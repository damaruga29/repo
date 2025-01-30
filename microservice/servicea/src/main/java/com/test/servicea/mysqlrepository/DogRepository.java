package com.test.servicea.mysqlrepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.servicea.entity.Dog;

@Repository("mysqlDogRepository")
public interface DogRepository extends JpaRepository<Dog, Long> {

	@Query("SELECT d FROM Dog d ORDER BY d.id DESC")
	List<Dog> findTopByOrderByIdDesc(Pageable pageable);

}
