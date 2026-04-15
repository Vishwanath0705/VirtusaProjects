use hospital_management_db;
--most consulted doctors
SELECT d.name,
    COUNT(*) AS total_visits
FROM doctors d
    JOIN appointments a on d.doctor_id = a.doctor_id
GROUP BY d.name
ORDER BY total_visits DESC;
--revenue per month
SELECT DATE_FORMAT(treatment_date, "%Y-%m") AS MONTH,
    SUM(cost) as revenue
from treatments
GROUP BY month;
--most common diseases
SELECT diagnosis,
    count(*) as disease_count
from treatments
GROUP BY diagnosis
ORDER BY disease_count DESC;
--most visits
SELECT p.name,
    count(*) as visits
from patients p
    join appointments a on p.patient_id = a.patient_id
GROUP BY p.name;
--doctor performance
SELECT d.name,
    count(DISTINCT a.patient_id) as patients
from doctors d
    join appointments a on d.doctor_id = a.doctor_id
GROUP BY d.name;