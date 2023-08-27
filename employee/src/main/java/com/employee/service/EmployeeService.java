package com.employee.service;

import java.util.List;

import com.employee.dto.ApiResponse;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

public interface EmployeeService {
    public List<EmployeeDto> getAll();

    public EmployeeDto create(Employee Employee);

    public ApiResponse getById(Long id);
}
