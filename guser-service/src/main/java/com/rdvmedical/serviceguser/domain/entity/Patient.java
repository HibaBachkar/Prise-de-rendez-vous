package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nom;
    
    @Column(nullable = false, length = 100)
    private String prenom;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "telephone", length = 20)
    private String telephone;
    
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @Column(name = "adresse", length = 255)
    private String adresse;
    
    @Column(name = "numero_secu", length = 50, unique = true)
    private String numeroSecu;
    
    @Column(name = "actif")
    private Boolean actif = true;
}

