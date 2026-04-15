import streamlit as st
import json
import re
import matplotlib.pyplot as plt

from utils import extract_text_from_pdf, extract_text_from_word, extract_sections
from ats_scoring import calculate_final_score, get_missing_skills

st.set_page_config(page_title="Resume Analyzer", layout="wide")

st.title("Resume Analyzer and Job Matcher (AI Powered)")

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


def plot_scores(result):
    labels = ["Skills", "Experience", "Projects", "Education"]

    values = [
        result.get("skill_score", 0),
        result.get("experience_score", 0),
        result.get("project_score", 0),
        result.get("education_score", 0)
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

    st.subheader("Resume Sections")

    sections = extract_sections(resume_text)

    for k, v in sections.items():
        if v.strip():
            with st.expander(k.capitalize()):
                st.write(v[:1000])

    st.subheader("ATS Score")

    result = calculate_final_score(resume_text, job_description_text)

    score = result["final_score"]

    st.metric("Final ATS Score", score)

    if score >= 80:
        st.success("Strong Match")
    elif score >= 60:
        st.warning("Moderate Match")
    else:
        st.error("Weak Match")

    st.subheader("Score Visualization")
    plot_scores(result)

    st.subheader("ATS Breakdown")

    st.write("Skill Score:", result["skill_score"])
    st.write("Experience Score:", result["experience_score"])
    st.write("Project Score:", result["project_score"])
    st.write("Education Score:", result["education_score"])

    st.subheader("Recommendations (Smart ATS Feedback)")

    skill_score = result.get("skill_score", 0)
    exp_score = result.get("experience_score", 0)
    proj_score = result.get("project_score", 0)
    edu_score = result.get("education_score", 0)

    missing_skills = get_missing_skills(resume_text, job_description_text)

    st.markdown("### 🚨 Missing Skills")
    if missing_skills:
        for s in missing_skills:
            st.write("• Add skill:", s)
    else:
        st.success("No major missing skills detected")

    st.markdown("### Improvement Suggestions")

    if skill_score < 60:
        st.warning("Improve your technical skills. Add missing skills from JD and build small projects.")

    if exp_score < 50:
        st.warning("Add internship experience or describe projects with strong action verbs like 'developed', 'built', 'deployed'.")

    if proj_score < 50:
        st.warning("Add 2–3 real-world projects (API, ML, Web apps, deployment).")

    if edu_score < 60:
        st.info("Education is fine, focus more on skills and projects.")

    st.markdown("### Suggested Projects")

    if "python" in missing_skills or "machine learning" in missing_skills:
        st.write("Build a Machine Learning Resume Classifier")
        st.write("Spam Email Detection System")

    if "react" in missing_skills:
        st.write("Build a React Portfolio Website")
        st.write("Todo App with React + API")

    if "api" in missing_skills:
        st.write("Build REST API using Flask or Node.js")