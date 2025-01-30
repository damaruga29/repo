package com.test.servicea.postgresrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.servicea.entity.Dog;

@Repository("postgresDogRepository")
public interface DogRepository extends JpaRepository<Dog, Long> {

}
