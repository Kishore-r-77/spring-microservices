package com.employee.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.dto.ApiResponse;
import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

     private WebClient webClient;

//    private ApiClient apiClient;
     
     
     private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
    
//    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponse getById(Long id) {
    	LOGGER.info("inside  getById() method");
        Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        // RestTemplate
        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
        // "http://localhost:8080/department/getByDeptCode/" +
        // employee.getDepartmentCode(),
        // DepartmentDto.class);

        // WebClient
         DepartmentDto departmentDto = webClient.get()
         .uri("http://localhost:8080/department/getByDeptCode/" +
         employee.getDepartmentCode())
         .retrieve()
         .bodyToMono(DepartmentDto.class)
         .block();
        
        //OpenFeign
//        ResponseEntity<DepartmentDto> responseEntity = apiClient.getDepartmentByDeptCode(employee.getDepartmentCode());
//        DepartmentDto departmentDto = responseEntity.getBody();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setDepartment(departmentDto);
        apiResponse.setEmployee(employeeDto);

        return apiResponse;
    }
    
    public ApiResponse getDefaultDepartment(Long id,Exception exception) {
    	LOGGER.info("inside  getDefaultDepartment() method");
    	Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        

        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setName("Default Dept");
        departmentDto.setCode("DCODE");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setDepartment(departmentDto);
        apiResponse.setEmployee(employeeDto);

        return apiResponse;
    }

}
