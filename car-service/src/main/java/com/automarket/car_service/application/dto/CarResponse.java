package com.automarket.car_service.application.dto;

import com.automarket.car_service.domain.model.CarStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CarResponse(
		Long id,
		String brand,
		String model,
		Integer year,
		BigDecimal price,
		Integer mileage,
		String fuelType,
		String transmission,
		CarStatus status,
		LocalDateTime createdAt) {
}
