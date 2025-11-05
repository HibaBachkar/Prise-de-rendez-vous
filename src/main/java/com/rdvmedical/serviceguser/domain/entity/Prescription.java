package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "prescription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consultation_id", nullable = false, unique = true)
    private Consultation consultation;
    
    @Column(name = "date_prescription", nullable = false)
    private LocalDate datePrescription;
    
    @Column(name = "medicaments", columnDefinition = "TEXT")
    private String medicaments;
    
    @Column(name = "posologie", columnDefinition = "TEXT")
    private String posologie;
    
    @Column(name = "duree_traitement")
    private Integer dureeTraitement; // en jours
    
    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;
    
    @Column(name = "valide")
    private Boolean valide = true;
}

