package com.test.servicea.entity;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "dog")
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
	
	@Id
    private Long id;
	
	private String name;
	private String cost;
	private String breed;
	
	@PostConstruct
    public void init() {
        System.out.println("init(): Dog bean is initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy(): Dog bean is about to be destroyed");
    }

}
