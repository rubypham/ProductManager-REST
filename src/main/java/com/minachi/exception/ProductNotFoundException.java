package com.minachi.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(long id) {
		super("Cound not find product with id = "+id);
	}
	
	
	
}
