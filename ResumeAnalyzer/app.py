from utils import extract_text_from_pdf, extract_skills, match_score, missing_skills


def main():
    resume_path = input("Enter resume PDF path: ")
    job_path = input("Enter job description file path: ")

    # Read files
    resume_text = extract_text_from_pdf(resume_path)
    with open(job_path, 'r') as f:
        job_text = f.read()

    # Extract skills
    resume_skills = extract_skills(resume_text)
    job_skills = extract_skills(job_text)

    # Calculate match
    score = match_score(resume_text, job_text)
    missing = missing_skills(resume_skills, job_skills)

    print("\n===== RESULT =====")
    print(f"Match Score: {score}%")
    print(f"\nResume Skills: {resume_skills}")
    print(f"\nJob Skills: {job_skills}")
    print(f"\nMissing Skills: {missing}")


if __name__ == "__main__":
    main()
