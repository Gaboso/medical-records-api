package com.github.gaboso.medicalrecord.exception;

public class EmptyFileException extends Exception {

    public EmptyFileException() {
        super("File is empty");
    }
}