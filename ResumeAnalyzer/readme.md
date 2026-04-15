# Resume Analyzer & Job Matcher

## Overview
This project evaluates a resume against a job description using a local ATS scoring system. It also includes an advanced AI-based enhancement system using Google Gemini AI for improved analysis and suggestions.

The system is divided into:
- Core ATS system (main project, no API required) -> which is done only based on functions and manual calculation of various factors that affect resume score.
- Advanced AI system (separate folder using Gemini AI) ->  in which ai handles everything from resume_score to improvements.

---

## Project Structure

ResumeAnalyzer/ |
|
|-- app.py            -> Main Streamlit UI (ATS system)
|-- ats_score.py      -> Core ATS scoring logic (skills, experience, projects, education)
|-- utils.py          -> Resume parsing and preprocessing utilities
|
|-- resume_analyzer_using_ai/ |
|   |-- appAi.py
|   |-- ai_ats.py
|   |-- ai_improvement_section.py
|
|-- requirements.txt

---

## Core ATS System

Files:
- app.py
- ats_score.py
- utils.py

Features:
- Resume upload (PDF / DOCX)
- Job description input
- Skill matching using rule-based logic
- Experience scoring
- Project and education scoring
- Final ATS score calculation
- Missing skill detection
- Visualization using Matplotlib

Advantages:
- No API required
- Fast and lightweight
- Works offline

---

## Advanced AI System

Folder:
resume_analyzer_using_ai/

---

## Run Project

Core system:
```
streamlit run app.py
```
AI system:
```
cd resume_analyzer_using_ai\
```
```
streamlit run appAi.py
```
---

## Features Summary

- ATS scoring system
- Skill gap detection
- Resume improvement suggestions
  
---

## AI Files (Advanced System)
Inside resume_analyzer_using_ai/:

- appAi.py -> AI powered UI using Gemini
- ai_ats.py -> AI based ATS evaluation
- ai_improvement_section.py -> AI resume improvement and suggestions
