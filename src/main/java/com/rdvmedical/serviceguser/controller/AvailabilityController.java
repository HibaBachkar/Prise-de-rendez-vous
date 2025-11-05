package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Availability;
import com.rdvmedical.serviceguser.service.IServiceAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    @Autowired
    private IServiceAvailability serviceAvailability;

    @GetMapping
    public ResponseEntity<List<Availability>> getAllAvailabilities() {
        List<Availability> availabilities = serviceAvailability.findAll();
        return ResponseEntity.ok(availabilities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Availability> getAvailabilityById(@PathVariable Long id) {
        return serviceAvailability.findById(id)
                .map(availability -> ResponseEntity.ok(availability))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByDoctor(@PathVariable Long doctorId) {
        List<Availability> availabilities = serviceAvailability.findByDoctorId(doctorId);
        return ResponseEntity.ok(availabilities);
    }

    @GetMapping("/doctor/{doctorId}/disponible/{disponible}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByDoctorAndDisponible(@PathVariable Long doctorId, 
                                                                                       @PathVariable Boolean disponible) {
        List<Availability> availabilities = serviceAvailability.findByDoctorIdAndDisponible(doctorId, disponible);
        return ResponseEntity.ok(availabilities);
    }

    @GetMapping("/doctor/{doctorId}/periode")
    public ResponseEntity<List<Availability>> getAvailabilitiesByDoctorAndPeriode(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Availability> availabilities = serviceAvailability.findByDoctorIdAndDateDebutBetween(doctorId, start, end);
        return ResponseEntity.ok(availabilities);
    }

    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability) {
        try {
            Availability createdAvailability = serviceAvailability.save(availability);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAvailability);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Long id, @RequestBody Availability availability) {
        try {
            Availability updatedAvailability = serviceAvailability.update(id, availability);
            return ResponseEntity.ok(updatedAvailability);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        try {
            serviceAvailability.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

