package com.automarket.car_service.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarCreateRequest(
		@NotBlank(message = "Marka boş olamaz") String brand,
		@NotBlank(message = "Model boş olamaz") String model,
		@NotNull(message = "Yıl zorunludur") @Min(value = 1886, message = "Yıl 1886 veya sonrası olmalıdır") @Max(value = 2100, message = "Yıl 2100 veya öncesi olmalıdır") Integer year,
		@NotNull(message = "Fiyat zorunludur") @DecimalMin(value = "0.0", inclusive = false, message = "Fiyat sıfırdan büyük olmalıdır") BigDecimal price,
		@NotNull(message = "Kilometre zorunludur") @Min(value = 0, message = "Kilometre negatif olamaz") Integer mileage,
		@NotBlank(message = "Yakıt tipi boş olamaz") String fuelType,
		@NotBlank(message = "Vites tipi boş olamaz") String transmission) {
}
