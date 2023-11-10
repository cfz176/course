package com.course.businesss.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidatorException.class)
    public ResponseDto ValidatorException(ValidatorException e) {
        return ResponseDto.errorResult(e.getMessage());
    }

}
