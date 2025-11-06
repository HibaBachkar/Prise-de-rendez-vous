package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO implements Serializable {
    private Long id;
    private String nom;
    private String numeroAssure;
    private Date dateDebut;
    private Date dateFin;
    private Long patientId;
    private String patientNom;
    private String patientPrenom;
}

