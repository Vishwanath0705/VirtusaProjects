from resume_analysing_using_gemini_ai.ai_ats import model

def ai_resume_improvement(resume_text, jd_text):

    prompt = f"""
You are a Tier-1 company resume optimization expert.

Your job is NOT to change truth — only improve presentation, ATS keywords, and structure.

You must provide:

### OUTPUT FORMAT (STRICT JSON):
{{
  "missing_skills": [],
  "skill_suggestions": [],
  "project_suggestions": [],
  "resume_improvements": [
    {{
      "original": "",
      "improved": "",
      "reason": ""
    }}
  ],
  "formatted_resume_snippets": [],
  "ats_boost_tips": []
}}

---

### RULES:
- Do NOT invent fake experience
- Do NOT exaggerate skills
- Improve wording using ATS keywords
- Convert weak sentences into strong impact statements
- Suggest 2–5 beginner-friendly projects relevant to JD

---

### EXAMPLES:

Original:
"worked on web project"

Improved:
"Developed a responsive full-stack web application using React and Node.js with REST API integration"

---

RESUME:
{resume_text}

JOB DESCRIPTION:
{jd_text}
"""

    response = model.generate_content(prompt)
    return response.text