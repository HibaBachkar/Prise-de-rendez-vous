package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DayOfWeek jourSemaine;
    
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;
    
    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;
    
    @Column(name = "duree_creneau")
    private Integer dureeCreneau = 30; // en minutes
}

