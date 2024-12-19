package com.test.reviewMS.util;

public record ApiResponse(
        String status,
        String message,
        Object data
) {
}
