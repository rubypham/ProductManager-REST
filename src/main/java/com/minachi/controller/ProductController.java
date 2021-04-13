package com.minachi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.minachi.entity.Product;
import com.minachi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ProductService productService;

	@GetMapping("/get")
	public List<Product> readProducts() {
		return productService.listAll();
	}

	@GetMapping("/get/{id}")
	public Product readProduct(@PathVariable(name = "id") long id) {
		return productService.find(id);
	}

	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product) {
		return productService.save(product);
	}

	@PutMapping("/update/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable(name = "id") long id) {
		return productService.update(product, id);
	}

	@RequestMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") long id) {
		productService.delete(id);
	}
}
