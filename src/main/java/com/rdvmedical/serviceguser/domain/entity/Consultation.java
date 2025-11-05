package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    private Appointment appointment;
    
    @Column(name = "date_consultation", nullable = false)
    private LocalDateTime dateConsultation;
    
    @Column(name = "compte_rendu", columnDefinition = "TEXT")
    private String compteRendu;
    
    @Column(name = "diagnostic", length = 500)
    private String diagnostic;
    
    @Column(name = "symptomes", columnDefinition = "TEXT")
    private String symptomes;
    
    @Column(name = "examen_clinique", columnDefinition = "TEXT")
    private String examenClinique;
    
    @Column(name = "prescription_recommandee")
    private Boolean prescriptionRecommandee = false;
}

