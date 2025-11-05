package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numeroRpps;
    private Long specialtyId;
    private String specialtyNom;
    private Boolean actif;
}

