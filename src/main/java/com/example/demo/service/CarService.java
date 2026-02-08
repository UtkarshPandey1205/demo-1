package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;

    // Constructor Injection
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Save new car
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Get car by id
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // Update car
    public Car updateCar(Long id, Car updatedCar) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setBrand(updatedCar.getBrand());
            car.setModel(updatedCar.getModel());
            car.setYear(updatedCar.getYear());
            car.setFuelType(updatedCar.getFuelType());
            car.setPrice(updatedCar.getPrice());
            car.setImageUrl(updatedCar.getImageUrl());
            return carRepository.save(car);
        }
        return null;
    }

    // Delete car
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Total number of cars
    public long getTotalCars() {
        return carRepository.count();
    }

    // Total inventory value
    public double getTotalInventoryValue() {
        return carRepository.findAll()
                .stream()
                .mapToDouble(Car::getPrice)
                .sum();
    }

    // Average car price ✅ (COMPLETED)
    public double getAverageCarPrice() {
        return carRepository.findAll()
                .stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }
}