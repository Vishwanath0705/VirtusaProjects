use hospital_management_db;
INSERT INTO patients (name, age, gender)
VALUES ('Ravi', 30, 'Male'),
    ('Anita', 25, 'Female'),
    ('John', 40, 'Male'),
    ('Priya', 35, 'Female'),
    ('Kiran', 28, 'Male'),
    ('Sneha', 32, 'Female'),
    ('Arjun', 45, 'Male'),
    ('Meena', 29, 'Female'),
    ('Rahul', 38, 'Male'),
    ('Divya', 27, 'Female');
INSERT INTO doctors (name, specialization)
VALUES ('Dr. Sharma', 'Cardiology'),
    ('Dr. Mehta', 'Orthopedics'),
    ('Dr. Reddy', 'General Medicine'),
    ('Dr. Patel', 'Dermatology'),
    ('Dr. Singh', 'Neurology');
INSERT INTO appointments (patient_id, doctor_id, date)
VALUES (1, 1, '2026-04-01'),
    (2, 3, '2026-04-02'),
    (3, 2, '2026-04-03'),
    (4, 4, '2026-04-04'),
    (5, 3, '2026-04-05'),
    (6, 1, '2026-04-06'),
    (7, 5, '2026-04-07'),
    (8, 2, '2026-04-08'),
    (9, 4, '2026-04-09'),
    (10, 3, '2026-04-10'),
    (1, 1, '2026-04-11'),
    (2, 3, '2026-04-12'),
    (3, 2, '2026-04-13');
INSERT INTO treatments (patient_id, diagnosis, cost, treatment_date)
VALUES (1, 'Heart Disease', 5000, '2026-04-01'),
    (2, 'Flu', 500, '2026-04-02'),
    (3, 'Fracture', 3000, '2026-04-03'),
    (4, 'Skin Allergy', 800, '2026-04-04'),
    (5, 'Fever', 600, '2026-04-05'),
    (6, 'Heart Disease', 4500, '2026-04-06'),
    (7, 'Migraine', 1200, '2026-04-07'),
    (8, 'Fracture', 3500, '2026-04-08'),
    (9, 'Skin Allergy', 900, '2026-04-09'),
    (10, 'Fever', 700, '2026-04-10'),
    (1, 'Heart Disease', 4000, '2026-04-11'),
    (2, 'Flu', 550, '2026-04-12'),
    (3, 'Fracture', 3200, '2026-04-13');