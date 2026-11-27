package com.automarket.car_service.application.service;

import com.automarket.car_service.application.dto.CarCreateRequest;
import com.automarket.car_service.application.dto.CarResponse;
import com.automarket.car_service.domain.exception.CarNotFoundException;
import com.automarket.car_service.domain.model.Car;
import com.automarket.car_service.infrastructure.persistence.CarRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {

	private final CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Transactional
	public CarResponse create(CarCreateRequest request) {
		Car car = new Car(
				request.brand().trim(),
				request.model().trim(),
				request.year(),
				request.price(),
				request.mileage(),
				request.fuelType().trim(),
				request.transmission().trim());
		return toResponse(carRepository.save(car));
	}

	public List<CarResponse> findAll() {
		return carRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
				.stream()
				.map(this::toResponse)
				.toList();
	}

	public CarResponse findById(Long id) {
		return toResponse(getCar(id));
	}

	@Transactional
	public CarResponse markAsSold(Long id) {
		Car car = getCar(id);
		car.markAsSold();
		return toResponse(car);
	}

	private Car getCar(Long id) {
		return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
	}

	private CarResponse toResponse(Car car) {
		return new CarResponse(
				car.getId(),
				car.getBrand(),
				car.getModel(),
				car.getYear(),
				car.getPrice(),
				car.getMileage(),
				car.getFuelType(),
				car.getTransmission(),
				car.getStatus(),
				car.getCreatedAt());
	}

}
