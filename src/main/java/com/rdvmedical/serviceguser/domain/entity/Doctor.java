package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable {
    
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
    
    @Column(name = "numero_rpps", length = 50, unique = true)
    private String numeroRpps;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;
    
    @Column(name = "actif")
    private Boolean actif = true;
}

