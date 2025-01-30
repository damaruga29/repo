package com.test.servicea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.test.servicea.entity.Dog;


@Service
public class DogService {
	
	@Autowired
	private com.test.servicea.mysqlrepository.DogRepository mysqlDogRepository;
	
	@Autowired
    private com.test.servicea.postgresrepository.DogRepository postgresDogRepository;
	
	@Autowired
    private ApplicationContext context;
	

	public Dog add(Dog dog) {
		Dog dogService= new Dog();
		Dog dogi = context.getBean(Dog.class);
		
		List<Dog> topDogs = mysqlDogRepository.findTopByOrderByIdDesc(PageRequest.of(0, 1));
		Long autoIncrementId = (topDogs.isEmpty()) ? 1L : topDogs.get(0).getId() + 1;
		
		dogService.setId(autoIncrementId);
		dogService.setBreed(dog.getBreed());
		dogService.setCost(dog.getCost());
		dogService.setName(dogi.getName());
		
		mysqlDogRepository.save(dogService);
		return postgresDogRepository.save(dogService);
	}

	public Dog update(Dog dog, Long id) {
		Dog dogService= mysqlDogRepository.findById(id).get();
		dogService.setName(dog.getName());
		dogService.setBreed(dog.getBreed());
		dogService.setCost(dog.getCost());
		
		postgresDogRepository.save(dogService);
		return mysqlDogRepository.save(dogService);
	}

	public List<Dog> getAll() {
		return postgresDogRepository.findAll().stream().filter(d-> d.getBreed().equals("bff"))
													   .map(d-> new Dog(null, d.getName(), d.getBreed(), null))
													   .collect(Collectors.toList());
	}

	public Optional<Dog> getById(Long id) {
		return postgresDogRepository.findById(id);
	}


	public void deleteById(Long id) {
		Optional.of(id).ifPresent(i-> {
			postgresDogRepository.deleteById(id);
			mysqlDogRepository.deleteById(id);
		});
	}

}
