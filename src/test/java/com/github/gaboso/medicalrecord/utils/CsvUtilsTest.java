package com.github.gaboso.medicalrecord.utils;

import com.github.gaboso.medicalrecord.exception.EmptyFileException;
import com.github.gaboso.medicalrecord.exception.UnsupportedFileException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

class CsvUtilsTest {

    private CsvUtils csvUtils;
    private AutoCloseable closeable;

    @Mock
    private MultipartFile file;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        csvUtils = new CsvUtils();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void validateFile_EmptyFile_ThrowsEmptyFileException() {
        Mockito.when(file.isEmpty()).thenReturn(true);
        Assertions.assertThrows(EmptyFileException.class, () -> csvUtils.validateFile(file));
    }

    @Test
    void validateFile_InvalidContentType_ThrowsUnsupportedFileException() {
        Mockito.when(file.isEmpty()).thenReturn(false);
        Mockito.when(file.getContentType()).thenReturn("text/xml");
        Assertions.assertThrows(UnsupportedFileException.class, () -> csvUtils.validateFile(file));
    }

    @Test
    void validateFile_validContentType_DoNotThrowsUnsupportedFileException() throws EmptyFileException, UnsupportedFileException {
        Mockito.when(file.isEmpty()).thenReturn(false);
        Mockito.when(file.getContentType()).thenReturn("text/csv");
        Assertions.assertDoesNotThrow(() -> csvUtils.validateFile(file));
    }

}