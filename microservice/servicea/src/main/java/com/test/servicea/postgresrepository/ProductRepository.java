package com.test.servicea.postgresrepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.servicea.entity.Product;


@Repository("postgresProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT d FROM Product d ORDER BY d.id DESC")
	List<Product> findTopByOrderByProductIdDesc(Pageable pageable);

}
