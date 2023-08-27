package com.department.service;

import java.util.List;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;

public interface DepartmentService {
    public List<DepartmentDto> getAll();

    public DepartmentDto create(Department department);

    public DepartmentDto getById(Long id);

    public DepartmentDto getByDeptCode(String deptCode);
}
