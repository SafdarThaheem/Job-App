package com.ms.companyMS.util;


public record ApiResponse(
        String status,
        String message,
        Object data
) {
}
