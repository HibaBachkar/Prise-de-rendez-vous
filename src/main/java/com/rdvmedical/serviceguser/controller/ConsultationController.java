package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.ConsultationDTO;
import com.rdvmedical.serviceguser.domain.entity.Consultation;
import com.rdvmedical.serviceguser.domain.mapper.ConsultationMapper;
import com.rdvmedical.serviceguser.service.IServiceConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private IServiceConsultation serviceConsultation;

    @Autowired
    private ConsultationMapper consultationMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        return serviceConsultation.findById(id)
                .map(consultation -> ResponseEntity.ok(consultationMapper.toDTO(consultation)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<ConsultationDTO> getConsultationByAppointment(@PathVariable Long appointmentId) {
        return serviceConsultation.findByAppointmentId(appointmentId)
                .map(consultation -> ResponseEntity.ok(consultationMapper.toDTO(consultation)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        try {
            Consultation consultation = consultationMapper.toEntity(consultationDTO);
            Consultation createdConsultation = serviceConsultation.save(consultation);
            ConsultationDTO createdDTO = consultationMapper.toDTO(createdConsultation);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO) {
        try {
            Consultation consultation = consultationMapper.toEntity(consultationDTO);
            Consultation updatedConsultation = serviceConsultation.update(id, consultation);
            ConsultationDTO updatedDTO = consultationMapper.toDTO(updatedConsultation);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        try {
            serviceConsultation.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
