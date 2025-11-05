package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Schedule;
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

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = serviceSchedule.findAll();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        return serviceSchedule.findById(id)
                .map(schedule -> ResponseEntity.ok(schedule))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Schedule>> getSchedulesByDoctor(@PathVariable Long doctorId) {
        List<Schedule> schedules = serviceSchedule.findByDoctorId(doctorId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/doctor/{doctorId}/jour/{jourSemaine}")
    public ResponseEntity<List<Schedule>> getSchedulesByDoctorAndJour(@PathVariable Long doctorId, 
                                                                      @PathVariable String jourSemaine) {
        java.time.DayOfWeek jour = java.time.DayOfWeek.valueOf(jourSemaine.toUpperCase());
        List<Schedule> schedules = serviceSchedule.findByDoctorIdAndJourSemaine(doctorId, jour);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule createdSchedule = serviceSchedule.save(schedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        try {
            Schedule updatedSchedule = serviceSchedule.update(id, schedule);
            return ResponseEntity.ok(updatedSchedule);
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

