package com.github.gaboso.medicalrecord.service;

import com.github.gaboso.medicalrecord.domain.dto.CsvDto;
import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordResponseDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import com.github.gaboso.medicalrecord.exception.NotFoundException;
import com.github.gaboso.medicalrecord.mapper.MedicalRecordMapper;
import com.github.gaboso.medicalrecord.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repository;
    private final MedicalRecordMapper mapper;

    public MedicalRecordService(MedicalRecordRepository repository, MedicalRecordMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public List<MedicalRecordResponseDto> saveAll(List<CsvDto> dtoList) {
        List<MedicalRecordEntity> entityList = mapper.toMedicalRecordEntityList(dtoList);
        List<MedicalRecordEntity> savedList = repository.saveAll(entityList);
        return mapper.toMedicalRecordDtoList(savedList);
    }

    public MedicalRecordResponseDto fetchByCode(String code) throws NotFoundException {
        MedicalRecordEntity entity = repository.findByCode(code)
                                               .orElseThrow(() -> new NotFoundException(code));

        return mapper.toMedicalRecordDto(entity);
    }

    public List<MedicalRecordResponseDto> fetchAll() {
        List<MedicalRecordEntity> entityList = repository.findAll();
        return mapper.toMedicalRecordDtoList(entityList);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}
