package com.github.gaboso.medicalrecord.controller;

import com.github.gaboso.medicalrecord.domain.dto.MedicalRecordDto;
import com.github.gaboso.medicalrecord.mapper.MedicalRecordMapper;
import com.github.gaboso.medicalrecord.service.MedicalRecordService;
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
    private final MedicalRecordMapper mapper;

    public MedicalRecordController(MedicalRecordService service, MedicalRecordMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(path = {"/upload"})
    public ResponseEntity<List<MedicalRecordDto>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        log.debug("REST request to upload csv file data");

        if (file.isEmpty()) {
            throw new Exception("File is empty.");
        }

        service.uploadCsvData();

        return null;
    }

    @GetMapping("/fetch/{code}")
    public ResponseEntity<MedicalRecordDto> fetchByCode(@PathVariable("code") String code) {
        log.debug("REST request to get medical records by `code`");

        MedicalRecordDto dto = service.fetchByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<List<MedicalRecordDto>> fetchAll() {
        log.debug("REST request to get all medical records");

        List<MedicalRecordDto> dtoList = service.fetchAll();
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
