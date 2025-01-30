package com.test.servicea.mysqlrepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.servicea.entity.OrderProduct;
import com.test.servicea.entity.OrderProductId;


@Repository("mysqlOrderProductRepository")
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

	@Query("SELECT d FROM OrderProduct d ORDER BY d.id DESC")
	List<OrderProduct> findTopByOrderProductByIdDesc(Pageable pageable);

}
