package com.test.servicea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.test.servicea.entity.Order;
import com.test.servicea.entity.OrderProduct;
import com.test.servicea.entity.OrderProductId;
import com.test.servicea.entity.Product;


@Service
public class ProductService {
	
	@Autowired
	com.test.servicea.mysqlrepository.OrderRepository mysqlOrderRepository;
	
	@Autowired
	com.test.servicea.mysqlrepository.ProductRepository mysqlProductRepository;
	
	@Autowired
	com.test.servicea.mysqlrepository.OrderProductRepository mysqlOrderProductRepository;
	
	
	@Autowired
	com.test.servicea.postgresrepository.OrderRepository postgresOrderRepository;
	
	@Autowired
	com.test.servicea.postgresrepository.ProductRepository postgresProductRepository;
	
	@Autowired
	com.test.servicea.postgresrepository.OrderProductRepository postgreOrderProductRepository;
	

	public List<OrderProduct> add(List<Product> product) {
		List<Order> topOrder = mysqlOrderRepository.findTopByOrderByOrderIdDesc(PageRequest.of(0, 1));
		Long orderAutoIncrementId = (topOrder.isEmpty()) ? 1L : topOrder.get(0).getOrderId() + 1;
		
		String orderId = UUID.randomUUID().toString();
		
		Order order= new Order();
		List<OrderProduct> orderProducts = new ArrayList<>();
		
		order.setOrderId(orderAutoIncrementId);
		order.setOrderUniqueId(orderId);
		
		mysqlOrderRepository.save(order);
		//postgresOrderRepository.save(order);
		
		Optional<Order> orderGetById= mysqlOrderRepository.findById(order.getOrderId());
		
		if(orderGetById.isPresent()) {
			Order orderIdAvailable= orderGetById.get();
			for(Product productItem: product) {
				List<Product> topProduct = mysqlProductRepository.findTopByOrderByProductIdDesc(PageRequest.of(0, 1));
				Long productAutoIncrementId = (topProduct.isEmpty()) ? 1L : topProduct.get(0).getProductId() + 1;
				
				productItem.setProductId(productAutoIncrementId);
				productItem.setProductName(productItem.getProductName());
				productItem.setPrice(productItem.getPrice());
				
				mysqlProductRepository.save(productItem);
				//postgresProductRepository.save(productItem);
				
				Optional<Product> productGetById= mysqlProductRepository.findById(productItem.getProductId());
				OrderProductId orderProductId = new OrderProductId(orderIdAvailable.getOrderId(), productGetById.get().getProductId());
				
				OrderProduct orderProduct = new OrderProduct();
				
	            orderProduct.setId(orderProductId);
	            orderProduct.setOrder(order);
	            orderProduct.setProduct(productItem);
	            orderProduct.setAnotherColumn("manju");

	            mysqlOrderProductRepository.save(orderProduct);
	            //postgreOrderProductRepository.save(orderProduct);
	            
	            orderProducts.add(orderProduct);
			}
		}
		return orderProducts;
	}

}
