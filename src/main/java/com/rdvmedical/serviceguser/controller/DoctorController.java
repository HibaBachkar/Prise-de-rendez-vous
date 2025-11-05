package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.service.IServiceDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private IServiceDoctor serviceDoctor;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = serviceDoctor.findAll();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return serviceDoctor.findById(id)
                .map(doctor -> ResponseEntity.ok(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) {
        return serviceDoctor.findByEmail(email)
                .map(doctor -> ResponseEntity.ok(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rpps/{numeroRpps}")
    public ResponseEntity<Doctor> getDoctorByNumeroRpps(@PathVariable String numeroRpps) {
        return serviceDoctor.findByNumeroRpps(numeroRpps)
                .map(doctor -> ResponseEntity.ok(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialty/{specialtyId}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@PathVariable Long specialtyId) {
        List<Doctor> doctors = serviceDoctor.findBySpecialtyId(specialtyId);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/actif/{actif}")
    public ResponseEntity<List<Doctor>> getDoctorsByActif(@PathVariable Boolean actif) {
        List<Doctor> doctors = serviceDoctor.findByActif(actif);
        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor createdDoctor = serviceDoctor.save(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        try {
            Doctor updatedDoctor = serviceDoctor.update(id, doctor);
            return ResponseEntity.ok(updatedDoctor);
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
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        try {
            serviceDoctor.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

