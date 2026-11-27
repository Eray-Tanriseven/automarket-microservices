package com.automarket.car_service.presentation.exception;

import com.automarket.car_service.domain.exception.CarAlreadySoldException;
import com.automarket.car_service.domain.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(CarNotFoundException exception) {
		return build(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(CarAlreadySoldException.class)
	public ResponseEntity<ApiError> handleAlreadySold(CarAlreadySoldException exception) {
		return build(HttpStatus.CONFLICT, exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
		String message = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.collect(Collectors.joining(", "));
		return build(HttpStatus.BAD_REQUEST, message);
	}

	private ResponseEntity<ApiError> build(HttpStatus status, String message) {
		ApiError body = new ApiError(status.value(), status.getReasonPhrase(), message, LocalDateTime.now());
		return ResponseEntity.status(status).body(body);
	}

}
