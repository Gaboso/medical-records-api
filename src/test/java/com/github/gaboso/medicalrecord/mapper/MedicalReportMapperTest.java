package com.github.gaboso.medicalrecord.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;

class MedicalReportMapperTest {

    private static MedicalReportMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(MedicalReportMapper.class);
    }



}