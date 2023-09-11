package com.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Department Dto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;
    private String code;

}
