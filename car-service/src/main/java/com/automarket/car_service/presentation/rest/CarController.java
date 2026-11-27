package com.automarket.car_service.presentation.rest;

import com.automarket.car_service.application.dto.CarCreateRequest;
import com.automarket.car_service.application.dto.CarResponse;
import com.automarket.car_service.application.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarResponse create(@Valid @RequestBody CarCreateRequest request) {
		return carService.create(request);
	}

	@GetMapping
	public List<CarResponse> findAll() {
		return carService.findAll();
	}

	@GetMapping("/{id}")
	public CarResponse findById(@PathVariable Long id) {
		return carService.findById(id);
	}

	@PutMapping("/{id}/sold")
	public CarResponse markAsSold(@PathVariable Long id) {
		return carService.markAsSold(id);
	}

}
