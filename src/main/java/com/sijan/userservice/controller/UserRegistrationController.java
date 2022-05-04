package com.sijan.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sijan.userservice.model.ResponseRecords;
import com.sijan.userservice.model.UserDomain;
import com.sijan.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class UserRegistrationController {
    private UserService userService;
    private ObjectMapper objectMapper;
    private DateFormat dateFormat;

    @PostMapping
    public ResponseEntity<ResponseRecords.ApiResponse> register(@RequestBody UserDomain request) {
        request.setId(null);
        log.info("got request [{}]", request);
        return userService.create(request).<ResponseEntity<ResponseRecords.ApiResponse>>map(
                userDomain -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseRecords.ApiSuccessResponse(
                                MDC.get("traceId"),
                                dateFormat.format(new Date()),
                                objectMapper.valueToTree(userDomain),
                                HttpStatus.CREATED
                        ))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseRecords.ApiErrorResponse(
                        MDC.get("traceId"),
                        dateFormat.format(new Date()),
                        "Something went wrong",
                        HttpStatus.OK
                )));
    }
}
