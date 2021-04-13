package com.minachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minachi.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
