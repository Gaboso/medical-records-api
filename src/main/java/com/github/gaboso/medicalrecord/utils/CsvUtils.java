package com.github.gaboso.medicalrecord.utils;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvUtils {

    public List<MedicalRecordDto> getDataFromFile(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());

        CsvToBean<MedicalRecordDto> csvToBean = new CsvToBeanBuilder(reader)
            .withSkipLines(1)
            .withType(MedicalRecordDto.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();
        return csvToBean.parse();
    }

}
