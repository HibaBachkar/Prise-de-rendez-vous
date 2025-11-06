package com.rdvmedical.serviceguser.client;

import com.rdvmedical.serviceguser.domain.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client Feign pour communiquer avec le service Patients
 * Le nom du service doit correspondre au nom enregistré dans Eureka
 */
@FeignClient(name = "service-patients", fallback = PatientServiceClientFallback.class)
public interface PatientServiceClient {

    /**
     * Récupère un patient par son ID
     * @param id ID du patient
     * @return PatientDTO
     */
    @GetMapping("/api/patients/{id}")
    PatientDTO getPatientById(@PathVariable Long id);

    /**
     * Vérifie si un patient existe
     * @param id ID du patient
     * @return true si le patient existe, false sinon
     */
    @GetMapping("/api/patients/{id}/exists")
    Boolean patientExists(@PathVariable Long id);
}

