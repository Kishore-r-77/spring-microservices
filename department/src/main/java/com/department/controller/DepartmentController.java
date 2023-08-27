package com.department.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.serviceimpl.DepartmentServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping("/getall")
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departmentList = departmentServiceImpl.getAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Type", "Department Get All");
        return new ResponseEntity<List<DepartmentDto>>(departmentList, headers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> create(@RequestBody Department department) {
        DepartmentDto saveDepartment = departmentServiceImpl.create(department);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable Long id) {
        DepartmentDto department = departmentServiceImpl.getById(id);
        return new ResponseEntity<DepartmentDto>(department, HttpStatus.OK);
    }

}
