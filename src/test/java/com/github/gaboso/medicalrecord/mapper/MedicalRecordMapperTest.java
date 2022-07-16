package com.github.gaboso.medicalrecord.mapper;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordResponseDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class MedicalRecordMapperTest {

    private static MedicalRecordMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(MedicalRecordMapper.class);
    }

    @Test
    void toMedicalRecordDto_ValidInput_IsProperlyMapped() {
        MedicalRecordEntity entity = buildValidEntity();
        MedicalRecordResponseDto expected = buildValidDto();
        MedicalRecordResponseDto result = mapper.toMedicalRecordDto(entity);

        Assertions.assertEquals(expected.getLongDescription(), result.getLongDescription(), "Result does not match Expected");
        Assertions.assertEquals(expected.getCode(), result.getCode(), "Result does not match Expected");
    }

    @Test
    void toMedicalRecordDto_NullInput_IsMappedAsNull() {
        MedicalRecordResponseDto result = mapper.toMedicalRecordDto(null);

        Assertions.assertNull(result, "Result does not match Expected");
    }

    @Test
    void toMedicalRecordEntity_ValidInput_IsProperlyMapped() throws Exception {
        MedicalRecordResponseDto dto = buildValidDto();
        MedicalRecordEntity expected = buildValidEntity();
        MedicalRecordEntity result = mapper.toMedicalRecordEntity(dto);

        Assertions.assertEquals(expected.getLongDescription(), result.getLongDescription(), "Result does not match Expected");
        Assertions.assertEquals(expected.getCode(), result.getCode(), "Result does not match Expected");
    }

    @Test
    void toMedicalRecordEntity_NullInput_IsMappedAsNull() {
        MedicalRecordEntity result = mapper.toMedicalRecordEntity(null);

        Assertions.assertNull(result, "Result does not match Expected");
    }

    private MedicalRecordEntity buildValidEntity() {
        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setCodeListCode("ZIB001");
        entity.setCode("271636001");
        entity.setDisplayValue("Polsslag regelmatig");
        entity.setSortingPriority(2);
        return entity;
    }

    private MedicalRecordResponseDto buildValidDto() {
        return MedicalRecordResponseDto.builder()
                                       .codeListCode("ZIB001")
                                       .code("271636001")
                                       .displayValue("Polsslag regelmatig")
                                       .sortingPriority(2)
                                       .build();
    }

}