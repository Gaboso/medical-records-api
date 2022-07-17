package com.github.gaboso.medicalrecord.mapper;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordResponseDto;
import com.github.gaboso.medicalrecord.domain.entity.MedicalRecordEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class MedicalRecordMapperTest {

    private static MedicalRecordMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(MedicalRecordMapper.class);
    }

    @Test
    void toMedicalRecordDto_ValidInput_IsProperlyMapped() throws Exception {
        MedicalRecordEntity entity = buildValidEntity();
        MedicalRecordResponseDto expected = buildValidDto();
        MedicalRecordResponseDto result = mapper.toMedicalRecordDto(entity);

        assertEqualsDto(expected, result);
    }

    private MedicalRecordEntity buildValidEntity() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String dateInString = "07-01-2022";
        Date date = formatter.parse(dateInString);

        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setSource("ZIB");
        entity.setCodeListCode("ZIB001");
        entity.setCode("271636001");
        entity.setDisplayValue("Polsslag regelmatig");
        entity.setFromDate(date);
        entity.setSortingPriority(2);
        return entity;
    }

    private MedicalRecordResponseDto buildValidDto() {
        return MedicalRecordResponseDto.builder()
                                       .source("ZIB")
                                       .codeListCode("ZIB001")
                                       .code("271636001")
                                       .displayValue("Polsslag regelmatig")
                                       .sortingPriority(2)
                                       .fromDate("07-01-2022")
                                       .build();
    }

    private void assertEqualsDto(MedicalRecordResponseDto expected, MedicalRecordResponseDto result) {
        Assertions.assertEquals(expected.getLongDescription(), result.getLongDescription());
        Assertions.assertEquals(expected.getCode(), result.getCode());
        Assertions.assertEquals(expected.getCodeListCode(), result.getCodeListCode());
        Assertions.assertEquals(expected.getFromDate(), result.getFromDate());
        Assertions.assertEquals(expected.getToDate(), result.getToDate());
        Assertions.assertEquals(expected.getDisplayValue(), result.getDisplayValue());
        Assertions.assertEquals(expected.getSortingPriority(), result.getSortingPriority());
        Assertions.assertEquals(expected.getSource(), result.getSource());
    }

    @Test
    void toMedicalRecordDto_NullInput_IsMappedAsNull() {
        MedicalRecordResponseDto result = mapper.toMedicalRecordDto(null);

        Assertions.assertNull(result);
    }

    @Test
    void toMedicalRecordDtoList_ValidInput_IsProperlyMapped() throws Exception {
        MedicalRecordEntity entity = buildValidEntity();

        List<MedicalRecordResponseDto> expected = List.of(buildValidDto());
        List<MedicalRecordResponseDto> result = mapper.toMedicalRecordDtoList(List.of(entity));

        assertEqualsDto(expected.get(0), result.get(0));
    }

    @Test
    void toMedicalRecordDtoList_NullInput_IsMappedAsNull() {
        List<MedicalRecordResponseDto> result = mapper.toMedicalRecordDtoList(null);

        Assertions.assertNull(result);
    }

    @Test
    void toMedicalRecordDtoList_EmptyInput_IsMappedAsEmptyList() {
        List<MedicalRecordResponseDto> result = mapper.toMedicalRecordDtoList(List.of());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

}