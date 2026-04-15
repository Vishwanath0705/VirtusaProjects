import os
from dotenv import load_dotenv
import google.generativeai as genai
import re
import json

load_dotenv()

API_KEY = os.getenv("GOOGLE_API_KEY")

if API_KEY is None:
    raise ValueError("GOOGLE_API_KEY not loaded. Check .env file location.")

def extract_json(text):
    try:
        return json.loads(text)
    except:
        match = re.search(r"\{.*\}", text, re.DOTALL)
        if match:
            return json.loads(match.group())
    return {}


genai.configure(api_key=API_KEY)

model = genai.GenerativeModel("gemini-pro-latest")


def ai_ats_analysis(resume_text, jd_text):

    prompt = f"""
You are an expert ATS system used by Tier-1 and Tier-2 tech companies (Google, Microsoft, Amazon, startups).

Your task is to evaluate a candidate resume against a job description in a STRICT, STRUCTURED, RECRUITER-LIKE manner.

---

### STEP 1: Extract Job Information
From the job description, identify:
- Job Title
- Required Skills
- Preferred Skills
- Experience Required (years + type)
- Technologies mentioned
- Role domain (ML / SDE / Data / Full-stack / etc.)

---

### STEP 2: Extract Resume Information
From the resume, identify:
- Candidate Skills
- Experience (internships, freelance, projects)
- Projects (technical depth + relevance)
- Certifications
- Tools & Technologies
- Domain classification

---

### STEP 3: Matching Logic (VERY IMPORTANT)
Now compare resume vs job description:

Evaluate separately:

1. Skill Match
   - Direct skill overlap
   - Partial/related skills
   - Missing critical skills

2. Experience Match
   - Relevance of internships/jobs/projects to JD role
   - Depth of experience (beginner / intermediate / advanced)
   - Industry relevance

3. Certification Match
   - Relevance of certifications to job role
   - Tier-1 company value alignment (AWS, ML, etc.)

4. Project Relevance
   - Real-world applicability
   - Complexity level
   - Alignment with JD requirements

---

### STEP 4: Tier-1 / Tier-2 Evaluation Thinking
Evaluate candidate like a recruiter from:
- Tier-1 companies (Google, Amazon, Microsoft)
- Tier-2 companies (product startups, service companies)

Be strict in evaluation:
- Do NOT overestimate beginner projects
- Give weight to real production experience
- Penalize missing system design / deployment skills if job expects it

---

### STEP 5: Scoring Rules
Generate scores (0–100):

- skill_score
- experience_score
- certification_score
- project_score
- final_ats_score (weighted)

Weights:
- Skills: 40%
- Experience: 30%
- Projects: 20%
- Certifications: 10%

---

### STEP 6: Output Format (STRICT JSON ONLY)

Return ONLY valid JSON:

{{
  "job_title": "",
  "candidate_domain": "",
  "required_skills": [],
  "missing_skills": [],
  "skill_match_score": 0,
  "experience_score": 0,
  "certification_score": 0,
  "project_score": 0,
  "final_ats_score": 0,
  "tier_evaluation": "",
  "strengths": [],
  "weaknesses": [],
  "detailed_feedback": "",
  "recommendations": [
    "improve X",
    "add Y",
    "learn Z"
  ]
}}

---

### RESUME:
{resume_text}

---

### JOB DESCRIPTION:
{jd_text}
"""

    response = model.generate_content(prompt)
    return response.text