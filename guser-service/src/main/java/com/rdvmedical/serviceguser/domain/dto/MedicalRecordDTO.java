package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO implements Serializable {
    private Long id;
    private Long patientId;
    private String patientNom;
    private String patientPrenom;
    private Date dateConsultation;
    private String diagnostic;
    private String symptomes;
    private String traitement;
    private String notes;
    private Long doctorId;
    private String doctorNom;
    private String doctorPrenom;
}

