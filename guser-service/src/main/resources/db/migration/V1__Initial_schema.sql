
CREATE TABLE IF NOT EXISTS specialty (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    INDEX idx_specialty_nom (nom)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    numero_rpps VARCHAR(50) UNIQUE,
    specialty_id BIGINT NOT NULL,
    actif BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (specialty_id) REFERENCES specialty(id) ON DELETE RESTRICT,
    INDEX idx_doctor_email (email),
    INDEX idx_doctor_rpps (numero_rpps),
    INDEX idx_doctor_specialty (specialty_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    jour_semaine VARCHAR(20) NOT NULL,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL,
    duree_creneau INT DEFAULT 30,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    INDEX idx_schedule_doctor (doctor_id),
    INDEX idx_schedule_jour (jour_semaine)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS availability (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    date_debut DATETIME NOT NULL,
    date_fin DATETIME NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    INDEX idx_availability_doctor (doctor_id),
    INDEX idx_availability_dates (date_debut, date_fin)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    date_naissance DATE,
    adresse VARCHAR(255),
    numero_secu VARCHAR(50) UNIQUE,
    actif BOOLEAN DEFAULT TRUE,
    INDEX idx_patient_email (email),
    INDEX idx_patient_secu (numero_secu)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS insurance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    numero_assure VARCHAR(50),
    date_debut DATE,
    date_fin DATE,
    patient_id BIGINT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    INDEX idx_insurance_patient (patient_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS medical_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    date_consultation DATE NOT NULL,
    diagnostic VARCHAR(500),
    symptomes TEXT,
    traitement TEXT,
    notes TEXT,
    doctor_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE SET NULL,
    INDEX idx_medical_record_patient (patient_id),
    INDEX idx_medical_record_doctor (doctor_id),
    INDEX idx_medical_record_date (date_consultation)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    date_heure DATETIME NOT NULL,
    duree INT DEFAULT 30,
    statut VARCHAR(20) DEFAULT 'PLANIFIE',
    motif VARCHAR(255),
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    INDEX idx_appointment_patient (patient_id),
    INDEX idx_appointment_doctor (doctor_id),
    INDEX idx_appointment_date (date_heure),
    INDEX idx_appointment_statut (statut)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS consultation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL UNIQUE,
    date_consultation DATETIME NOT NULL,
    compte_rendu TEXT,
    diagnostic VARCHAR(500),
    symptomes TEXT,
    examen_clinique TEXT,
    prescription_recommandee BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE CASCADE,
    INDEX idx_consultation_appointment (appointment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS prescription (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    consultation_id BIGINT NOT NULL UNIQUE,
    date_prescription DATE NOT NULL,
    medicaments TEXT,
    posologie TEXT,
    duree_traitement INT,
    instructions TEXT,
    valide BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (consultation_id) REFERENCES consultation(id) ON DELETE CASCADE,
    INDEX idx_prescription_consultation (consultation_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO specialty (nom, description) VALUES
('Cardiologie', 'Spécialité médicale concernant le cœur et les vaisseaux sanguins'),
('Dermatologie', 'Spécialité médicale concernant la peau, les muqueuses et les phanères'),
('Neurologie', 'Spécialité médicale concernant le système nerveux'),
('Pédiatrie', 'Spécialité médicale concernant les enfants'),
('Médecine générale', 'Spécialité médicale de premier recours')
ON DUPLICATE KEY UPDATE nom=nom;
