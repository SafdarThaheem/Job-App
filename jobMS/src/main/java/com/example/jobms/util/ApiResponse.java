package com.example.jobms.util;


public record ApiResponse(
        String status,
        String message,
        Object data
) {
}
