package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.ScheduleDTO;
import com.rdvmedical.serviceguser.domain.entity.Schedule;
import com.rdvmedical.serviceguser.domain.mapper.ScheduleMapper;
import com.rdvmedical.serviceguser.service.IServiceSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private IServiceSchedule serviceSchedule;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<Schedule> schedules = serviceSchedule.findAll();
        List<ScheduleDTO> scheduleDTOs = scheduleMapper.toDTOList(schedules);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        return serviceSchedule.findById(id)
                .map(schedule -> ResponseEntity.ok(scheduleMapper.toDTO(schedule)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDoctor(@PathVariable Long doctorId) {
        List<Schedule> schedules = serviceSchedule.findByDoctorId(doctorId);
        List<ScheduleDTO> scheduleDTOs = scheduleMapper.toDTOList(schedules);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping("/doctor/{doctorId}/jour/{jourSemaine}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDoctorAndJour(@PathVariable Long doctorId, 
                                                                      @PathVariable String jourSemaine) {
        java.time.DayOfWeek jour = java.time.DayOfWeek.valueOf(jourSemaine.toUpperCase());
        List<Schedule> schedules = serviceSchedule.findByDoctorIdAndJourSemaine(doctorId, jour);
        List<ScheduleDTO> scheduleDTOs = scheduleMapper.toDTOList(schedules);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
            Schedule createdSchedule = serviceSchedule.save(schedule);
            ScheduleDTO createdDTO = scheduleMapper.toDTO(createdSchedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
            Schedule updatedSchedule = serviceSchedule.update(id, schedule);
            ScheduleDTO updatedDTO = scheduleMapper.toDTO(updatedSchedule);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        try {
            serviceSchedule.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
