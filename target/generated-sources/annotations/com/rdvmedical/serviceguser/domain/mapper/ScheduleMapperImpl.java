package com.rdvmedical.serviceguser.domain.mapper;

import com.rdvmedical.serviceguser.domain.dto.ScheduleDTO;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.entity.Schedule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T00:37:03+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleDTO toDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setDoctorId( scheduleDoctorId( schedule ) );
        scheduleDTO.setDoctorNom( scheduleDoctorNom( schedule ) );
        scheduleDTO.setDoctorPrenom( scheduleDoctorPrenom( schedule ) );
        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.setJourSemaine( schedule.getJourSemaine() );
        scheduleDTO.setHeureDebut( schedule.getHeureDebut() );
        scheduleDTO.setHeureFin( schedule.getHeureFin() );
        scheduleDTO.setDureeCreneau( schedule.getDureeCreneau() );

        return scheduleDTO;
    }

    @Override
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setDoctor( scheduleDTOToDoctor( scheduleDTO ) );
        schedule.setId( scheduleDTO.getId() );
        schedule.setJourSemaine( scheduleDTO.getJourSemaine() );
        schedule.setHeureDebut( scheduleDTO.getHeureDebut() );
        schedule.setHeureFin( scheduleDTO.getHeureFin() );
        schedule.setDureeCreneau( scheduleDTO.getDureeCreneau() );

        return schedule;
    }

    @Override
    public List<ScheduleDTO> toDTOList(List<Schedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( toDTO( schedule ) );
        }

        return list;
    }

    @Override
    public List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOs) {
        if ( scheduleDTOs == null ) {
            return null;
        }

        List<Schedule> list = new ArrayList<Schedule>( scheduleDTOs.size() );
        for ( ScheduleDTO scheduleDTO : scheduleDTOs ) {
            list.add( toEntity( scheduleDTO ) );
        }

        return list;
    }

    private Long scheduleDoctorId(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }
        Doctor doctor = schedule.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String scheduleDoctorNom(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }
        Doctor doctor = schedule.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String nom = doctor.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private String scheduleDoctorPrenom(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }
        Doctor doctor = schedule.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String prenom = doctor.getPrenom();
        if ( prenom == null ) {
            return null;
        }
        return prenom;
    }

    protected Doctor scheduleDTOToDoctor(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( scheduleDTO.getDoctorId() );

        return doctor;
    }
}
