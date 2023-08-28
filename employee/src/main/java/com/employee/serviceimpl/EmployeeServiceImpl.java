package com.employee.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.dto.ApiResponse;
import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.service.ApiClient;
import com.employee.service.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

    // private WebClient webClient;

    private ApiClient apiClient;

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
    public ApiResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        // RestTemplate
        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
        // "http://localhost:8080/department/getByDeptCode/" +
        // employee.getDepartmentCode(),
        // DepartmentDto.class);

        // WebClient
        // DepartmentDto departmentDto = webClient.get()
        // .uri("http://localhost:8080/department/getByDeptCode/" +
        // employee.getDepartmentCode())
        // .retrieve()
        // .bodyToMono(DepartmentDto.class)
        // .block();

        ResponseEntity<DepartmentDto> responseEntity = apiClient.getDepartmentByDeptCode(employee.getDepartmentCode());
        DepartmentDto departmentDto = responseEntity.getBody();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setDepartment(departmentDto);
        apiResponse.setEmployee(employeeDto);

        return apiResponse;
    }

}
