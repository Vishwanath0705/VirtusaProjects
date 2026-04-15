# this is using gemini ai

import streamlit as st
import json
import re
import matplotlib.pyplot as plt

from utils import extract_text_from_pdf, extract_text_from_word, data_clean, extract_sections
from resume_analysing_using_gemini_ai.ai_ats import ai_ats_analysis
from resume_analysing_using_gemini_ai.ai_improvement_section import ai_resume_improvement

st.set_page_config(page_title="Resume Analyzer", layout="wide")

st.title("Resume Analyzer and Job Matcher (AI Powered)")

if "analysis_done" not in st.session_state:
    st.session_state.analysis_done = False

if "resume_text" not in st.session_state:
    st.session_state.resume_text = ""

if "jd_text" not in st.session_state:
    st.session_state.jd_text = ""


st.header("Upload Resume")
resume_file = st.file_uploader("Upload Resume (PDF or DOCX)", type=["pdf", "docx"])

st.header("Job Description")
jd_option = st.radio("Choose input method:", ["Paste Job Description", "Upload Job Description File"])
job_description_text = ""

if jd_option == "Paste Job Description":
    job_description_text = st.text_area("Paste Job Description Here", height=250)
else:
    jd_file = st.file_uploader("Upload Job Description File (TXT)", type=["txt"])
    if jd_file:
        job_description_text = jd_file.read().decode("utf-8")


def extract_json(text):
    try:
        return json.loads(text)
    except:
        match = re.search(r"\{.*\}", text, re.DOTALL)
        if match:
            return json.loads(match.group())
    return {}


def plot_scores(result):
    labels = ["Skills", "Experience", "Projects", "Certifications"]

    values = [
        result.get("skill_match_score", 0),
        result.get("experience_score", 0),
        result.get("project_score", 0),
        result.get("certification_score", 0)
    ]

    fig, ax = plt.subplots(figsize=(8, 4))
    ax.bar(labels, values)
    ax.set_ylim(0, 100)
    ax.set_title("ATS Score Breakdown")
    ax.set_ylabel("Score")
    ax.grid(axis="y", linestyle="--", alpha=0.5)

    st.pyplot(fig)


if st.button("Analyze Resume"):

    if not resume_file:
        st.error("Upload resume")
        st.stop()

    if not job_description_text.strip():
        st.error("Upload JD")
        st.stop()

    if resume_file.name.endswith(".pdf"):
        resume_text = extract_text_from_pdf(resume_file)
    else:
        resume_text = extract_text_from_word(resume_file)

    st.session_state.resume_text = resume_text
    st.session_state.jd_text = job_description_text

    st.subheader("Resume Sections")

    sections = extract_sections(resume_text)

    for k, v in sections.items():
        if v.strip():
            with st.expander(k.capitalize()):
                st.write(v[:1000])

    st.subheader("AI ATS Analysis")

    ai_result = ai_ats_analysis(resume_text, job_description_text)
    result = extract_json(ai_result)

    st.code(ai_result, language="json")

    score = result.get("final_ats_score", 0)

    st.metric("Final ATS Score", score)

    if score >= 80:
        st.success("Strong Match")
    elif score >= 60:
        st.warning("Moderate Match")
    else:
        st.error("Weak Match")

    st.subheader("Score Visualization")
    plot_scores(result)

    st.subheader("Recommendations")

    recommendations = result.get("recommendations", [])
    selected = st.selectbox("Choose a recommendation", recommendations if recommendations else ["No recommendations"])
    st.info(selected)

    st.subheader("AI Resume Improvement")

    improve_result = ai_resume_improvement(resume_text, job_description_text)
    improve_data = extract_json(improve_result)

    st.code(improve_result, language="json")

    st.markdown("### Missing Skills")
    for i in improve_data.get("missing_skills", []):
        st.write("•", i)

    st.markdown("### Skill Suggestions")
    for i in improve_data.get("skill_suggestions", []):
        st.write("•", i)

    st.markdown("### Project Ideas")
    for i in improve_data.get("project_suggestions", []):
        st.write("•", i)

    st.markdown("### Resume Improvements")

    for item in improve_data.get("resume_improvements", []):
        st.markdown("Original")
        st.write(item.get("original", ""))

        st.markdown("Improved")
        st.success(item.get("improved", ""))

        st.markdown("Reason")
        st.info(item.get("reason", ""))

    st.markdown("### ATS Boost Tips")

    for i in improve_data.get("ats_boost_tips", []):
        st.write("•", i)
