package com.github.gaboso.medicalrecord.controller;

import com.github.gaboso.medicalrecord.domain.dto.CsvDto;
import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordResponseDto;
import com.github.gaboso.medicalrecord.service.MedicalRecordService;
import com.github.gaboso.medicalrecord.utils.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class MedicalRecordController {

    private final MedicalRecordService service;
    private final CsvUtils csvUtils;

    public MedicalRecordController(MedicalRecordService service, CsvUtils csvUtils) {
        this.service = service;
        this.csvUtils = csvUtils;
    }

    @PostMapping(path = {"/upload"})
    public ResponseEntity<List<MedicalRecordResponseDto>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        log.debug("REST request to upload csv file data");

        if (file.isEmpty()) {
            throw new Exception("File is empty.");
        }

        List<CsvDto> dtoList = csvUtils.getDataFromFile(file);
        List<MedicalRecordResponseDto> savedDtoList = service.saveAll(dtoList);

        return ResponseEntity.ok(savedDtoList);
    }

    @GetMapping("/fetch/{code}")
    public ResponseEntity<MedicalRecordResponseDto> fetchByCode(@PathVariable("code") String code) {
        log.debug("REST request to get medical records by `code`");

        MedicalRecordResponseDto dto = service.fetchByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<List<MedicalRecordResponseDto>> fetchAll() {
        log.debug("REST request to get all medical records");

        List<MedicalRecordResponseDto> dtoList = service.fetchAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        log.debug("REST request to delete all medical records");

        service.deleteAll();
        return ResponseEntity.noContent()
                             .build();
    }

}
