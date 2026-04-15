CREATE DATABASE IF NOT EXISTS hospital_management_db;
use hospital_management_db;
CREATE TABLE IF NOT EXISTS patients(
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(15)
);
CREATE TABLE IF NOT EXISTS doctors(
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS appointments(
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
CREATE TABLE IF NOT EXISTS treatments(
    treatment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    diagnosis VARCHAR(250),
    cost DECIMAL(10, 2),
    treatment_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);