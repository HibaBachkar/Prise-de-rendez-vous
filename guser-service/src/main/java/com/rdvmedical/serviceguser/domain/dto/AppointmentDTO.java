package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO implements Serializable {
    private Long id;
    private Long patientId;
    private String patientNom;
    private String patientPrenom;
    private Long doctorId;
    private String doctorNom;
    private String doctorPrenom;
    private LocalDateTime dateHeure;
    private Integer duree;
    private String statut;
    private String motif;
    private String notes;
}

