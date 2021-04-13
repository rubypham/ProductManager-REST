package com.minachi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.minachi.entity.Employee;
import com.minachi.exception.EmployeeNotFoundException;
import com.minachi.repository.EmployeeRepository;

@Transactional
@Service
public class EmployeeService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeRepository repository;

	public CollectionModel<EntityModel<Employee>> all() {

		List<EntityModel<Employee>> employees = repository.findAll().stream().map(employee -> EntityModel.of(employee
//						,
//						linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
//						linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
		)).collect(Collectors.toList());

		return CollectionModel.of(employees
//				,
//				linkTo(
//						methodOn(EmployeeController.class)
//								.all()
//				).withSelfRel()
		);
	}
	// end::get-aggregate-root[]

	public Employee create(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	public EntityModel<Employee> get(@PathVariable Long id) {

		Employee employee = repository.findById(id) //
				.orElseThrow(() -> new EmployeeNotFoundException(id));

		return EntityModel.of(employee
//				, //
//				linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//				linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
		);

	}

	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id) //
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				}) //
				.orElse(create(newEmployee));
	}

	public void deleteEmployee(@PathVariable Long id) {
		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		repository.delete(employee);
	}
}
