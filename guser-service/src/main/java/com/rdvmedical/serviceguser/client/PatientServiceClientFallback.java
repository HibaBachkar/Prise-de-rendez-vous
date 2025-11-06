package com.rdvmedical.serviceguser.client;

import com.rdvmedical.serviceguser.domain.dto.PatientDTO;
import org.springframework.stereotype.Component;

/**
 * Classe de fallback pour PatientServiceClient
 * Utilisée en cas d'échec de communication avec le service Patients
 */
@Component
public class PatientServiceClientFallback implements PatientServiceClient {

    @Override
    public PatientDTO getPatientById(Long id) {
        // Retourne null en cas d'échec
        // En production, on pourrait logger l'erreur ou retourner une valeur par défaut
        return null;
    }

    @Override
    public Boolean patientExists(Long id) {
        // Retourne false par défaut en cas d'échec
        return false;
    }
}

