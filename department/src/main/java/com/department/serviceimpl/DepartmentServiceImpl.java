package com.department.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.mapper.AutoDepartmentMapper;
import com.department.repository.DepartmentRepository;
import com.department.service.DepartmentService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = departmentList.stream()
                .map((deparment) -> AutoDepartmentMapper.MAPPER.mapToDepartmentDto(deparment))
                .collect(Collectors.toList());
        return departmentDtoList;
    }

    @Override
    @Transactional
    public DepartmentDto create(Department department) {
        Department departmentBody = departmentRepository.save(department);
        DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(departmentBody);
        return departmentDto;

    }

    @Override
    public DepartmentDto getById(Long id) {
        Department department = departmentRepository.findById(id).get();
        DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
        return departmentDto;
    }

    @Override
    public DepartmentDto getByDeptCode(String deptCode) {
        Department department = departmentRepository.getByDeptCode(deptCode);
        DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

        return departmentDto;
    }

}
