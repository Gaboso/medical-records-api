package com.github.gaboso.medicalrecord.service;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import com.github.gaboso.medicalrecord.mapper.MedicalRecordMapper;
import com.github.gaboso.medicalrecord.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repository;
    private final MedicalRecordMapper mapper;

    public MedicalRecordService(MedicalRecordRepository repository, MedicalRecordMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MedicalRecordDto> uploadCsvData() {
        return null;
    }

    public MedicalRecordDto fetchByCode(String code) {
        MedicalRecordEntity entity = repository.findByCode(code)
                                               .orElseThrow();

        return mapper.toMedicalRecordDto(entity);
    }

    public List<MedicalRecordDto> fetchAll() {
        List<MedicalRecordEntity> entityList = repository.findAll();
        return mapper.toMedicalRecordDtoList(entityList);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
