package com.automarket.car_service.domain.exception;

public class CarNotFoundException extends RuntimeException {

	public CarNotFoundException(Long id) {
		super("Araba ilanı bulunamadı: " + id);
	}

}
