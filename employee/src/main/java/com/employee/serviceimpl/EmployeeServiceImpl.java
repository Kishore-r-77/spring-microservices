package com.employee.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = employeeList.stream()
                .map((deparment) -> AutoEmployeeMapper.MAPPER.mapToEmployeeDto(deparment))
                .collect(Collectors.toList());
        return employeeDtoList;
    }

    @Override
    @Transactional
    public EmployeeDto create(Employee employee) {
        Employee employeeBody = employeeRepository.save(employee);
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employeeBody);
        return employeeDto;

    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        return employeeDto;
    }

}
