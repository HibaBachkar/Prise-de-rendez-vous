package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.InsuranceDTO;
import com.rdvmedical.serviceguser.domain.entity.Insurance;
import com.rdvmedical.serviceguser.domain.mapper.InsuranceMapper;
import com.rdvmedical.serviceguser.service.IServiceInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    @Autowired
    private IServiceInsurance serviceInsurance;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @GetMapping
    public ResponseEntity<List<InsuranceDTO>> getAllInsurances() {
        List<Insurance> insurances = serviceInsurance.findAll();
        List<InsuranceDTO> insuranceDTOs = insuranceMapper.toDTOList(insurances);
        return ResponseEntity.ok(insuranceDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDTO> getInsuranceById(@PathVariable Long id) {
        return serviceInsurance.findById(id)
                .map(insurance -> ResponseEntity.ok(insuranceMapper.toDTO(insurance)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InsuranceDTO>> getInsurancesByPatient(@PathVariable Long patientId) {
        List<Insurance> insurances = serviceInsurance.findByPatientId(patientId);
        List<InsuranceDTO> insuranceDTOs = insuranceMapper.toDTOList(insurances);
        return ResponseEntity.ok(insuranceDTOs);
    }

    @PostMapping
    public ResponseEntity<InsuranceDTO> createInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        try {
            Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
            Insurance createdInsurance = serviceInsurance.save(insurance);
            InsuranceDTO createdDTO = insuranceMapper.toDTO(createdInsurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceDTO> updateInsurance(@PathVariable Long id, @RequestBody InsuranceDTO insuranceDTO) {
        try {
            Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
            Insurance updatedInsurance = serviceInsurance.update(id, insurance);
            InsuranceDTO updatedDTO = insuranceMapper.toDTO(updatedInsurance);
            return ResponseEntity.ok(updatedDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        try {
            serviceInsurance.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
