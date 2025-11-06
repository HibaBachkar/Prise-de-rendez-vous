package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medical_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(name = "date_consultation", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;
    
    @Column(name = "diagnostic", length = 500)
    private String diagnostic;
    
    @Column(name = "symptomes", columnDefinition = "TEXT")
    private String symptomes;
    
    @Column(name = "traitement", columnDefinition = "TEXT")
    private String traitement;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

