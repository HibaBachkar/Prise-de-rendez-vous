package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface IServiceSpecialty {
    List<Specialty> findAll();
    Optional<Specialty> findById(Long id);
    Optional<Specialty> findByNom(String nom);
    Specialty save(Specialty specialty);
    Specialty update(Long id, Specialty specialty);
    void deleteById(Long id);
    boolean existsById(Long id);
}

