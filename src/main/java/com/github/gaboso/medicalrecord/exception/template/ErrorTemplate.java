package com.github.gaboso.medicalrecord.exception.template;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ErrorTemplate {

    private final String errorCode;
    private final String errorMessage;

}