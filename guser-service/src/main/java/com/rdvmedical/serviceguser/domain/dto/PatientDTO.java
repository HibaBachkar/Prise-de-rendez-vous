package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateNaissance;
    private String adresse;
    private String numeroSecu;
    private Boolean actif;
}

