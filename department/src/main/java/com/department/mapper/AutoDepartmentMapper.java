package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;

@Mapper(componentModel = "spring")
public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);

    @Mapping(source = "departmentName", target = "name")
    @Mapping(source = "departmentCode", target = "code")
    DepartmentDto mapToDepartmentDto(Department department);

    @Mapping(source = "name", target = "departmentName")
    @Mapping(source = "code", target = "departmentCode")
    Department mapToDepartmentEntity(DepartmentDto departmentDto);
}
