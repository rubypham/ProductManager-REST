package com.minachi.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2087100243114428758L;

	public EmployeeNotFoundException(Long id) {
		super("Could not find employee " + id);
	}
}
