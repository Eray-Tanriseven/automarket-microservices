package com.automarket.car_service.presentation.exception;

import java.time.LocalDateTime;

public record ApiError(int status, String error, String message, LocalDateTime timestamp) {
}
