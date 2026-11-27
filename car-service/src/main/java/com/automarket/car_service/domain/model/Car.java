package com.automarket.car_service.domain.model;

import com.automarket.car_service.domain.exception.CarAlreadySoldException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String brand;

	@Column(nullable = false, length = 100)
	private String model;

	@Column(name = "model_year", nullable = false)
	private Integer year;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer mileage;

	@Column(nullable = false, length = 50)
	private String fuelType;

	@Column(nullable = false, length = 50)
	private String transmission;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private CarStatus status;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	protected Car() {
	}

	public Car(String brand, String model, Integer year, BigDecimal price, Integer mileage, String fuelType,
			String transmission) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.price = price;
		this.mileage = mileage;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.status = CarStatus.AVAILABLE;
	}

	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
		if (this.status == null) {
			this.status = CarStatus.AVAILABLE;
		}
	}

	public void markAsSold() {
		if (this.status == CarStatus.SOLD) {
			throw new CarAlreadySoldException(this.id);
		}
		this.status = CarStatus.SOLD;
	}

	public Long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public Integer getYear() {
		return year;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getMileage() {
		return mileage;
	}

	public String getFuelType() {
		return fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public CarStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
