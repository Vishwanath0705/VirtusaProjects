import re
from collections import Counter


def tokenize(text):
    text = text.lower()
    text = re.sub(r"[^a-z0-9\s]", " ", text)
    return set(text.split())


def extract_keywords(text):
    common_noise = {
        "the", "is", "and", "to", "in", "for", "on", "a", "an",
        "with", "of", "by", "as", "at", "from"
    }
    words = tokenize(text)
    return words - common_noise


SKILL_DB = {
    "python": ["python", "py"],
    "java": ["java"],
    "c++": ["c++", "cpp"],
    "javascript": ["javascript", "js"],
    "react": ["react", "reactjs", "react.js"],
    "node": ["node", "nodejs", "node.js"],
    "django": ["django"],
    "flask": ["flask"],
    "machine learning": ["ml", "machine learning", "ai model"],
    "deep learning": ["deep learning", "dl"],
    "sql": ["sql", "mysql", "postgres"],
    "mongodb": ["mongodb", "mongo"],
    "html": ["html"],
    "css": ["css"],
    "api": ["api", "rest api", "rest"],
    "docker": ["docker"],
    "git": ["git", "github"]
}


def normalize(text):
    text = text.lower()
    text = re.sub(r"[^a-z0-9\s]", " ", text)
    return text


def calculate_skill_score(resume_text, jd_text):

    resume_text = normalize(resume_text)
    jd_text = normalize(jd_text)

    jd_skills = []
    resume_skills = []

    for skill, variants in SKILL_DB.items():

        if any(v in jd_text for v in variants):
            jd_skills.append(skill)

        if any(v in resume_text for v in variants):
            resume_skills.append(skill)

    if not jd_skills:
        return 0

    matched = set(resume_skills).intersection(set(jd_skills))

    score = (len(matched) / len(jd_skills)) * 100

    return round(min(score, 100), 2)


def calculate_experience_score(text):
    text = text.lower()

    score = 0

    experience_patterns = {
        "intern": 15,
        "internship": 15,
        "built": 20,
        "developed": 25,
        "designed": 20,
        "deployed": 25,
        "implemented": 20,
        "project": 10
    }

    for key, value in experience_patterns.items():
        if key in text:
            score += value

    numbers = re.findall(r"\d+", text)
    if numbers:
        score += min(len(numbers) * 5, 20)

    return min(score, 100)


def calculate_education_score(text):
    text = text.lower()

    if any(x in text for x in ["bachelor", "b.tech", "btech", "engineering"]):
        return 100
    if any(x in text for x in ["master", "m.tech", "msc"]):
        return 90
    if "diploma" in text:
        return 70
    if "school" in text or "12th" in text:
        return 40

    return 20


def calculate_project_score(text):
    text = text.lower()

    score = 0

    project_keywords = {
        "ml": 20,
        "machine learning": 25,
        "ai": 20,
        "api": 15,
        "web": 10,
        "flask": 15,
        "django": 15,
        "react": 15,
        "node": 15,
        "deployed": 20,
        "database": 10
    }

    for key, value in project_keywords.items():
        if key in text:
            score += value

    return min(score, 100)


def calculate_final_score(resume_text, jd_text):
    skill = calculate_skill_score(resume_text, jd_text)
    exp = calculate_experience_score(resume_text)
    edu = calculate_education_score(resume_text)
    proj = calculate_project_score(resume_text)

    final = (
        0.45 * skill +
        0.25 * exp +
        0.20 * proj +
        0.10 * edu
    )

    return {
        "skill_score": skill,
        "experience_score": exp,
        "project_score": proj,
        "education_score": edu,
        "final_score": round(final, 2)
    }

def get_missing_skills(resume_text, jd_text):

    resume_text = normalize(resume_text)
    jd_text = normalize(jd_text)

    missing = []

    for skill, variants in SKILL_DB.items():
        if any(v in jd_text for v in variants):
            if not any(v in resume_text for v in variants):
                missing.append(skill)

    return missing

