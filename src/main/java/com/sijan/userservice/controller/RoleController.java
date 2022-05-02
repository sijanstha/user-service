package com.sijan.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sijan.userservice.model.ResponseRecords;
import com.sijan.userservice.model.RoleDomain;
import com.sijan.userservice.model.RoleListResponse;
import com.sijan.userservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/role")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;
    private DateFormat dateFormat;
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ResponseRecords.ApiResponse> create(@RequestBody RoleDomain request) {
        Optional<RoleDomain> optionalRoleDomain = roleService.create(request);
        if (optionalRoleDomain.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseRecords.ApiErrorResponse(MDC.get("requestId"),
                            dateFormat.format(new Date()),
                            "Something went wrong",
                            HttpStatus.INTERNAL_SERVER_ERROR));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseRecords.ApiSuccessResponse(
                        MDC.get("traceId"),
                        dateFormat.format(new Date()),
                        objectMapper.valueToTree(optionalRoleDomain.get()),
                        HttpStatus.CREATED
                ));
    }

    @GetMapping
    public ResponseEntity<ResponseRecords.ApiResponse> getAll() {
        var response = new RoleListResponse(roleService.findAll());
        return ResponseEntity.ok(
                new ResponseRecords.ApiSuccessResponse(
                        MDC.get("traceId"),
                        dateFormat.format(new Date()),
                        objectMapper.valueToTree(response),
                        HttpStatus.OK
                )
        );
    }
}
