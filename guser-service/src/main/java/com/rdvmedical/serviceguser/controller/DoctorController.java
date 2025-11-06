package com.rdvmedical.serviceguser.controller;

import com.rdvmedical.serviceguser.domain.dto.DoctorDTO;
import com.rdvmedical.serviceguser.domain.entity.Doctor;
import com.rdvmedical.serviceguser.domain.mapper.DoctorMapper;
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

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = serviceDoctor.findAll();
        List<DoctorDTO> doctorDTOs = doctorMapper.toDTOList(doctors);
        return ResponseEntity.ok(doctorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return serviceDoctor.findById(id)
                .map(doctor -> ResponseEntity.ok(doctorMapper.toDTO(doctor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorDTO> getDoctorByEmail(@PathVariable String email) {
        return serviceDoctor.findByEmail(email)
                .map(doctor -> ResponseEntity.ok(doctorMapper.toDTO(doctor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rpps/{numeroRpps}")
    public ResponseEntity<DoctorDTO> getDoctorByNumeroRpps(@PathVariable String numeroRpps) {
        return serviceDoctor.findByNumeroRpps(numeroRpps)
                .map(doctor -> ResponseEntity.ok(doctorMapper.toDTO(doctor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialty/{specialtyId}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(@PathVariable Long specialtyId) {
        List<Doctor> doctors = serviceDoctor.findBySpecialtyId(specialtyId);
        List<DoctorDTO> doctorDTOs = doctorMapper.toDTOList(doctors);
        return ResponseEntity.ok(doctorDTOs);
    }

    @GetMapping("/actif/{actif}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsByActif(@PathVariable Boolean actif) {
        List<Doctor> doctors = serviceDoctor.findByActif(actif);
        List<DoctorDTO> doctorDTOs = doctorMapper.toDTOList(doctors);
        return ResponseEntity.ok(doctorDTOs);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            Doctor doctor = doctorMapper.toEntity(doctorDTO);
            Doctor createdDoctor = serviceDoctor.save(doctor);
            DoctorDTO createdDTO = doctorMapper.toDTO(createdDoctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        try {
            Doctor doctor = doctorMapper.toEntity(doctorDTO);
            Doctor updatedDoctor = serviceDoctor.update(id, doctor);
            DoctorDTO updatedDTO = doctorMapper.toDTO(updatedDoctor);
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
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        try {
            serviceDoctor.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
