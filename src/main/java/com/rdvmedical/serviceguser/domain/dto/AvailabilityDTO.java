package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDTO implements Serializable {
    private Long id;
    private Long doctorId;
    private String doctorNom;
    private String doctorPrenom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Boolean disponible;
}

