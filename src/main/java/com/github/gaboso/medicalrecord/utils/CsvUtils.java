package com.github.gaboso.medicalrecord.utils;

import com.github.gaboso.medicalrecord.domain.dto.CsvDto;
import com.github.gaboso.medicalrecord.exception.EmptyFileException;
import com.github.gaboso.medicalrecord.exception.UnsupportedFileException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvUtils {

    public List<CsvDto> getDataFromFile(MultipartFile file) throws IOException {
        try (
            InputStream inputStream = file.getInputStream();
            Reader reader = new InputStreamReader(inputStream)
        ) {
            CsvToBean<CsvDto> csvToBean = new CsvToBeanBuilder(reader)
                .withSkipLines(1)
                .withType(CsvDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
            return csvToBean.parse();
        }
    }

    public void validateFile(MultipartFile file) throws EmptyFileException, UnsupportedFileException {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }

        if (!"text/csv".equals(file.getContentType())) {
            throw new UnsupportedFileException();
        }
    }

}
