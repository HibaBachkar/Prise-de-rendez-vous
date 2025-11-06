package com.rdvmedical.serviceguser.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "specialty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialty implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String nom;
    
    @Column(length = 500)
    private String description;
}

