package com.rdvmedical.serviceguser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO implements Serializable {
    private Long id;
    private Long doctorId;
    private String doctorNom;
    private String doctorPrenom;
    private DayOfWeek jourSemaine;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Integer dureeCreneau;
}

