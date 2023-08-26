package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.entity.Department;

/**
 * DepartmentRepository
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
