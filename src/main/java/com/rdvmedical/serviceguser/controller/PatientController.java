package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.PatientDTO;
import com.rdvmedical.serviceguser.domain.entity.Patient;
import com.rdvmedical.serviceguser.domain.mapper.PatientMapper;
import com.rdvmedical.serviceguser.service.IServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private IServicePatient servicePatient;

    @Autowired
    private PatientMapper patientMapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Patient> patients = servicePatient.findAll();
        List<PatientDTO> patientDTOs = patientMapper.toDTOList(patients);
        return ResponseEntity.ok(patientDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        return servicePatient.findById(id)
                .map(patient -> ResponseEntity.ok(patientMapper.toDTO(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PatientDTO> getPatientByEmail(@PathVariable String email) {
        return servicePatient.findByEmail(email)
                .map(patient -> ResponseEntity.ok(patientMapper.toDTO(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/secu/{numeroSecu}")
    public ResponseEntity<PatientDTO> getPatientByNumeroSecu(@PathVariable String numeroSecu) {
        return servicePatient.findByNumeroSecu(numeroSecu)
                .map(patient -> ResponseEntity.ok(patientMapper.toDTO(patient)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientMapper.toEntity(patientDTO);
            Patient createdPatient = servicePatient.save(patient);
            PatientDTO createdDTO = patientMapper.toDTO(createdPatient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        try {
            Patient patient = patientMapper.toEntity(patientDTO);
            Patient updatedPatient = servicePatient.update(id, patient);
            PatientDTO updatedDTO = patientMapper.toDTO(updatedPatient);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("non trouvé")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        try {
            servicePatient.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
