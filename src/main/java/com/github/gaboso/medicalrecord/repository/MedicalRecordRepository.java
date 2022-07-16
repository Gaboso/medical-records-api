package com.github.gaboso.medicalrecord.repository;

import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {

    Optional<MedicalRecordEntity> findByCode(String code);
}