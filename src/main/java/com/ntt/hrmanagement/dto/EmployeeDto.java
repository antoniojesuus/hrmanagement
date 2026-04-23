package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {

    private Long id;
    private String name;
    private String position;
    private Double salary;
    private EmployeeStatus status;
    private String departmentName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String position, Double salary, EmployeeStatus status, String departmentName) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.departmentName = departmentName;
    }

}