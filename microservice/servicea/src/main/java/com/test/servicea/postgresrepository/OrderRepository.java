package com.test.servicea.postgresrepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.servicea.entity.Order;


@Repository("postgresOrderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT d FROM Order d ORDER BY d.id DESC")
	List<Order> findTopByOrderByOrderIdDesc(Pageable pageable);

}
