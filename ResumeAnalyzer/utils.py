import spacy
from PyPDF2 import PdfReader
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

nlp = spacy.load("en_core_web_sm")


def extract_text_from_pdf(file_path):
    reader = PdfReader(file_path)
    text = ""
    for page in reader.pages:
        text += page.extract_text()
    return text


def extract_skills(text):
    doc = nlp(text.lower())
    skills = []
    for token in doc:
        if token.pos_ in ["NOUN", "PROPN"]:
            skills.append(token.text)
    return list(set(skills))


def match_score(resume_text, job_text):
    cv = CountVectorizer()
    matrix = cv.fit_transform([resume_text, job_text])
    similarity = cosine_similarity(matrix)[0][1]
    return round(similarity * 100, 2)


def missing_skills(resume_skills, job_skills):
    return list(set(job_skills) - set(resume_skills))
