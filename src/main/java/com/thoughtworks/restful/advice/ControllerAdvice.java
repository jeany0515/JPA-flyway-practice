package com.thoughtworks.restful.advice;

import com.thoughtworks.restful.exception.CompanyNotFoundException;
import com.thoughtworks.restful.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({CompanyNotFoundException.class, EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notfoundExceptionHandler(Exception exception) {
        return new ErrorResponse("404", exception.getMessage());

    }
}
