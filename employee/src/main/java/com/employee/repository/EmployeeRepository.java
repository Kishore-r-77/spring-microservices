package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.Employee;

// Employee Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
