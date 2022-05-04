package com.sijan.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sijan.userservice.model.ResponseRecords;
import com.sijan.userservice.model.UserAuthenticationRequest;
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
@RequestMapping("/api/authenticate-user")
@AllArgsConstructor
public class UserAuthenticationController {

    private UserService userService;
    private DateFormat dateFormat;
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ResponseRecords.ApiResponse> checkForAuthentication(@RequestBody UserAuthenticationRequest request) {
        log.info("got request [{}]", request);
        UserDomain userDomain = userService.checkUserForAuthentication(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new ResponseRecords.ApiSuccessResponse(
                MDC.get("traceId"),
                dateFormat.format(new Date()),
                objectMapper.valueToTree(userDomain),
                HttpStatus.OK
        ));
    }
}
