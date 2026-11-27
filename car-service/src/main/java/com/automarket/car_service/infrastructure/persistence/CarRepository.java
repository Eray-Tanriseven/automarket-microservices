package com.automarket.car_service.infrastructure.persistence;

import com.automarket.car_service.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
