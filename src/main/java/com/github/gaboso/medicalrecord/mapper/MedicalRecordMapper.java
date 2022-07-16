package com.github.gaboso.medicalrecord.mapper;

import com.github.gaboso.medicalrecord.domain.dto.CsvDto;
import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordResponseDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalRecordMapper {

    MedicalRecordResponseDto toMedicalRecordDto(MedicalRecordEntity entity);

    List<MedicalRecordResponseDto> toMedicalRecordDtoList(List<MedicalRecordEntity> entityList);

    MedicalRecordEntity toMedicalRecordEntity(MedicalRecordResponseDto dto);
    List<MedicalRecordEntity> toMedicalRecordEntityList(List<CsvDto> dtoList);

}