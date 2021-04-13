package com.minachi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minachi.entity.Product;
import com.minachi.exception.ProductNotFoundException;
import com.minachi.rediscache.Cache;
import com.minachi.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String KEY = "com.minachi.service.ProductService.#id";

	@Autowired
	private ProductRepository repo;

	public List<Product> listAll() {
		return repo.findAll();
	}

	@Cacheable(cacheNames = Cache.DEFAULT_NAME, key = KEY)
	public Product find(long id) {
		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	public Product save(Product product) {
		return repo.save(product);
	}

	@Caching(evict = { @CacheEvict(cacheNames = Cache.DEFAULT_NAME, key = KEY), })
	public void delete(long id) {
		repo.delete(find(id));
	}

	@CacheEvict(cacheNames = Cache.DEFAULT_NAME, key = KEY)
	public Product update(Product newProduct, long id) {
		// if found --> update, else create a new one

		return repo.findById(id).map(product -> {
			update(product, newProduct);
			return repo.save(product);
		}).orElse(save(newProduct));

	}

	private void update(Product existingProduct, Product newProduct) {
		existingProduct.setName(newProduct.getName());
		existingProduct.setBrand(newProduct.getBrand());
	}

}
