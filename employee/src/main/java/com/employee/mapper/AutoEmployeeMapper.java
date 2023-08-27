package com.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;

@Mapper(componentModel = "spring")
public interface AutoEmployeeMapper {

    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToEmployeeEntity(EmployeeDto employeedDto);

}
