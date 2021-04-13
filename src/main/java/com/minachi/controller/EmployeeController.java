package com.minachi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minachi.entity.Employee;
import com.minachi.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/get")
	public CollectionModel<EntityModel<Employee>> get() {
		LOGGER.info("Getting all");
		return employeeService.all();
	}

	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee newEmployee) {

		return employeeService.create(newEmployee);
	}

	@GetMapping("/get/{id}")
	public EntityModel<Employee> get(@PathVariable Long id) {
		return employeeService.get(id);

	}

	@PutMapping("/update/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeService.updateEmployee(newEmployee, id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}
}
