package com.test.servicea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.servicea.entity.OrderProduct;
import com.test.servicea.entity.Product;
import com.test.servicea.service.ProductService;

@RestController
@RequestMapping(value= "orderService")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping("/makeOrder")
	public List<OrderProduct> add(@RequestBody List<Product> product) {
		return productService.add(product);
	}

}
