
CREATE INDEX IF NOT EXISTS idx_appointment_doctor_date ON appointment(doctor_id, date_heure);
CREATE INDEX IF NOT EXISTS idx_appointment_patient_date ON appointment(patient_id, date_heure);
CREATE INDEX IF NOT EXISTS idx_availability_doctor_disponible ON availability(doctor_id, disponible);


CREATE INDEX IF NOT EXISTS idx_appointment_statut_date ON appointment(statut, date_heure);

CREATE INDEX IF NOT EXISTS idx_patient_nom_prenom ON patient(nom, prenom);

CREATE INDEX IF NOT EXISTS idx_doctor_specialty_actif ON doctor(specialty_id, actif);

CREATE INDEX IF NOT EXISTS idx_medical_record_date_desc ON medical_record(patient_id, date_consultation DESC);

