package com.rdvmedical.serviceguser.service;

import com.rdvmedical.serviceguser.domain.entity.Insurance;

import java.util.List;
import java.util.Optional;

public interface IServiceInsurance {
    List<Insurance> findAll();
    Optional<Insurance> findById(Long id);
    List<Insurance> findByPatientId(Long patientId);
    Insurance save(Insurance insurance);
    Insurance update(Long id, Insurance insurance);
    void deleteById(Long id);
    boolean existsById(Long id);
}

