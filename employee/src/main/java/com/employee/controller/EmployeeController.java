package com.employee.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.serviceimpl.EmployeeServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/getall")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> employeeList = employeeServiceImpl.getAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Type", "Employee Get All");
        return new ResponseEntity<List<EmployeeDto>>(employeeList, headers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(@RequestBody Employee employee) {
        EmployeeDto saveEmployee = employeeServiceImpl.create(employee);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<EmployeeDto> getById(@RequestParam Long id) {
        EmployeeDto employee = employeeServiceImpl.getById(id);
        return new ResponseEntity<EmployeeDto>(employee, HttpStatus.OK);
    }

}