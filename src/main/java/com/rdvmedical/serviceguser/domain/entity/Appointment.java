package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @Column(name = "date_heure", nullable = false)
    private LocalDateTime dateHeure;
    
    @Column(name = "duree")
    private Integer duree = 30; // en minutes
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", length = 20)
    private AppointmentStatus statut = AppointmentStatus.PLANIFIE;
    
    @Column(name = "motif", length = 255)
    private String motif;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    public enum AppointmentStatus {
        PLANIFIE,
        CONFIRME,
        EN_COURS,
        TERMINE,
        ANNULE
    }
}

