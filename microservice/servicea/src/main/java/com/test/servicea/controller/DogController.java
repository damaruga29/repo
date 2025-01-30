package com.test.servicea.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.test.servicea.entity.Dog;
import com.test.servicea.service.DogService;

@RestController
@RequestMapping(value= "/dogService")
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	
	@PostMapping("/add")
	public Dog add(@RequestBody Dog dog) {
		return dogService.add(dog);
	}
	
	@PutMapping("/update/{id}")
	public Dog update(@RequestBody Dog dog, @PathVariable Long id) {
		return dogService.update(dog, id);
	}
	
	@GetMapping("/getAll")
	public List<Dog> getAll() {
		return dogService.getAll();
	}
	
	@GetMapping("/getById/{id}")
	public Optional<Dog> getById(@PathVariable Long id) {
		return dogService.getById(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable Long id) {
		dogService.deleteById(id);
		return "Record deleted successfully";
	}
	
}
