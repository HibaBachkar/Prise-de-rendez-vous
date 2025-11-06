package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.AvailabilityDTO;
import com.rdvmedical.serviceguser.domain.entity.Availability;
import com.rdvmedical.serviceguser.domain.mapper.AvailabilityMapper;
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

    @Autowired
    private AvailabilityMapper availabilityMapper;

    @GetMapping
    public ResponseEntity<List<AvailabilityDTO>> getAllAvailabilities() {
        List<Availability> availabilities = serviceAvailability.findAll();
        List<AvailabilityDTO> availabilityDTOs = availabilityMapper.toDTOList(availabilities);
        return ResponseEntity.ok(availabilityDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable Long id) {
        return serviceAvailability.findById(id)
                .map(availability -> ResponseEntity.ok(availabilityMapper.toDTO(availability)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesByDoctor(@PathVariable Long doctorId) {
        List<Availability> availabilities = serviceAvailability.findByDoctorId(doctorId);
        List<AvailabilityDTO> availabilityDTOs = availabilityMapper.toDTOList(availabilities);
        return ResponseEntity.ok(availabilityDTOs);
    }

    @GetMapping("/doctor/{doctorId}/disponible/{disponible}")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesByDoctorAndDisponible(@PathVariable Long doctorId, 
                                                                                       @PathVariable Boolean disponible) {
        List<Availability> availabilities = serviceAvailability.findByDoctorIdAndDisponible(doctorId, disponible);
        List<AvailabilityDTO> availabilityDTOs = availabilityMapper.toDTOList(availabilities);
        return ResponseEntity.ok(availabilityDTOs);
    }

    @GetMapping("/doctor/{doctorId}/periode")
    public ResponseEntity<List<AvailabilityDTO>> getAvailabilitiesByDoctorAndPeriode(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Availability> availabilities = serviceAvailability.findByDoctorIdAndDateDebutBetween(doctorId, start, end);
        List<AvailabilityDTO> availabilityDTOs = availabilityMapper.toDTOList(availabilities);
        return ResponseEntity.ok(availabilityDTOs);
    }

    @PostMapping
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        try {
            Availability availability = availabilityMapper.toEntity(availabilityDTO);
            Availability createdAvailability = serviceAvailability.save(availability);
            AvailabilityDTO createdDTO = availabilityMapper.toDTO(createdAvailability);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailability(@PathVariable Long id, @RequestBody AvailabilityDTO availabilityDTO) {
        try {
            Availability availability = availabilityMapper.toEntity(availabilityDTO);
            Availability updatedAvailability = serviceAvailability.update(id, availability);
            AvailabilityDTO updatedDTO = availabilityMapper.toDTO(updatedAvailability);
            return ResponseEntity.ok(updatedDTO);
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
