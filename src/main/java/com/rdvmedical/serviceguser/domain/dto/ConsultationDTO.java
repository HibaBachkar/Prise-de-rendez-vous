package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO implements Serializable {
    private Long id;
    private Long appointmentId;
    private LocalDateTime dateConsultation;
    private String compteRendu;
    private String diagnostic;
    private String symptomes;
    private String examenClinique;
    private Boolean prescriptionRecommandee;
}

