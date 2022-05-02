package com.sijan.userservice.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;

public class ResponseRecords {

    public interface ApiResponse {
        String requestId();
        String timestamp();
    }

    public record ApiSuccessResponse(String requestId, String timestamp, ObjectNode body, HttpStatus status) implements ApiResponse{
    }

    public record ApiErrorResponse(String requestId, String timestamp, String message, HttpStatus status) implements ApiResponse {
    }
}
