package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.department.entity.Department;

//Department Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "Select * from department where department_code=:deptCode", nativeQuery = true)
    Department getByDeptCode(String deptCode);

}
