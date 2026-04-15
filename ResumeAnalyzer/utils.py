from PyPDF2 import PdfReader
import docx
import re

def extract_text_from_pdf(file):
    text = ""
    reader = PdfReader(file)

    for page in reader.pages:
        content = page.extract_text()
        if content:
            text += content + "\n"

    return text


def extract_text_from_word(file):
    text = ""
    doc = docx.Document(file)

    for para in doc.paragraphs:
        text += para.text + "\n"

    return text


def data_clean(text):
    text = text.lower()
    text = re.sub(r"[^a-zA-Z0-9\n\s]", " ", text)
    text = re.sub(r"\s+", " ", text)
    return text.strip()


SECTIONS = {
    "education": ["education", "academic"],
    "experience": ["experience", "work experience", "internship"],
    "projects": ["projects", "project"],
    "skills": ["skills", "technical skills"],
    "certifications": ["certifications", "certificates"],
    "achievements": ["achievements", "awards"]
}


def extract_sections(text):
    sections = {key: "" for key in SECTIONS}
    current_section = None

    for line in text.split("\n"):
        line_lower = line.lower().strip()

        for section, keywords in SECTIONS.items():
            if any(k in line_lower for k in keywords):
                current_section = section
                break

        if current_section:
            sections[current_section] += line + "\n"

    return sections