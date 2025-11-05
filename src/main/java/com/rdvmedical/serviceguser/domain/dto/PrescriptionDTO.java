package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO implements Serializable {
    private Long id;
    private Long consultationId;
    private LocalDate datePrescription;
    private String medicaments;
    private String posologie;
    private Integer dureeTraitement;
    private String instructions;
    private Boolean valide;
}

