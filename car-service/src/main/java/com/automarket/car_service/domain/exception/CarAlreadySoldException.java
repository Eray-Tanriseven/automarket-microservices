package com.automarket.car_service.domain.exception;

public class CarAlreadySoldException extends RuntimeException {

	public CarAlreadySoldException(Long id) {
		super("Araba ilanı zaten satılmış: " + id);
	}

}
