package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Specialty;
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

    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialties() {
        List<Specialty> specialties = serviceSpecialty.findAll();
        return ResponseEntity.ok(specialties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable Long id) {
        return serviceSpecialty.findById(id)
                .map(specialty -> ResponseEntity.ok(specialty))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Specialty> getSpecialtyByNom(@PathVariable String nom) {
        return serviceSpecialty.findByNom(nom)
                .map(specialty -> ResponseEntity.ok(specialty))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Specialty> createSpecialty(@RequestBody Specialty specialty) {
        try {
            Specialty createdSpecialty = serviceSpecialty.save(specialty);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecialty);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable Long id, @RequestBody Specialty specialty) {
        try {
            Specialty updatedSpecialty = serviceSpecialty.update(id, specialty);
            return ResponseEntity.ok(updatedSpecialty);
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

