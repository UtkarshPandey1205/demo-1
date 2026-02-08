package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.Car;

//@RestController → returns JSON
//
//@Controller → returns HTML pages
@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    // Home page → list of cars
    @GetMapping("/")
    public String viewHomePage(Model model) {

        List<Car> cars = carService.getAllCars();

        model.addAttribute("cars", cars);
        model.addAttribute("totalCars", carService.getTotalCars());
        model.addAttribute("totalValue", carService.getTotalInventoryValue());
        model.addAttribute("avgPrice", carService.getAverageCarPrice());

        return "car-list";
    }

	//  Call viewHomePage()
	//  Fetch cars from DB
    

    // Delete car
    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
    
 // Show add car form
    @GetMapping("/showNewCarForm")
    public String showNewCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "add-car";
    }

    // Save car
    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.saveCar(car);
        return "redirect:/";
    }
    
 // Show edit car form
    @GetMapping("/showEditCarForm/{id}")
    public String showEditCarForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "edit-car";
    }

}