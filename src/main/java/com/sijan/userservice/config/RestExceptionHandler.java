package com.sijan.userservice.config;

import com.sijan.userservice.model.ResponseRecords;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.DateFormat;
import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private DateFormat dateFormat;

    @ExceptionHandler(value = IllegalStateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseRecords.ApiErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseRecords.ApiErrorResponse(
                        MDC.get("traceId"),
                        dateFormat.format(new Date()),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST
                ));
    }
}
