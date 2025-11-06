package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.SpecialtyDTO;
import com.rdvmedical.serviceguser.domain.entity.Specialty;
import com.rdvmedical.serviceguser.domain.mapper.SpecialtyMapper;
import com.rdvmedical.serviceguser.service.IServiceSpecialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    @Autowired
    private IServiceSpecialty serviceSpecialty;

    @Autowired
    private SpecialtyMapper specialtyMapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<Specialty> specialties = serviceSpecialty.findAll();
        List<SpecialtyDTO> specialtyDTOs = specialtyMapper.toDTOList(specialties);
        return ResponseEntity.ok(specialtyDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Long id) {
        return serviceSpecialty.findById(id)
                .map(specialty -> ResponseEntity.ok(specialtyMapper.toDTO(specialty)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<SpecialtyDTO> getSpecialtyByNom(@PathVariable String nom) {
        return serviceSpecialty.findByNom(nom)
                .map(specialty -> ResponseEntity.ok(specialtyMapper.toDTO(specialty)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        try {
            Specialty specialty = specialtyMapper.toEntity(specialtyDTO);
            Specialty createdSpecialty = serviceSpecialty.save(specialty);
            SpecialtyDTO createdDTO = specialtyMapper.toDTO(createdSpecialty);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> updateSpecialty(@PathVariable Long id, @RequestBody SpecialtyDTO specialtyDTO) {
        try {
            Specialty specialty = specialtyMapper.toEntity(specialtyDTO);
            Specialty updatedSpecialty = serviceSpecialty.update(id, specialty);
            SpecialtyDTO updatedDTO = specialtyMapper.toDTO(updatedSpecialty);
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
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        try {
            serviceSpecialty.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

