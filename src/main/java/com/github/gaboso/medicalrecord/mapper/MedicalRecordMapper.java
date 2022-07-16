package com.github.gaboso.medicalrecord.mapper;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalRecordMapper {

    MedicalRecordDto toMedicalRecordDto(MedicalRecordEntity entity);

    List<MedicalRecordDto> toMedicalRecordDtoList(List<MedicalRecordEntity> entityList);

    MedicalRecordEntity toMedicalRecordEntity(MedicalRecordDto dto);

    List<MedicalRecordEntity> toMedicalRecordEntityList(List<MedicalRecordDto> dtoList);

}