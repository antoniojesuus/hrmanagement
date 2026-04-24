package com.ntt.hrmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDTO {

    private Long id;
    private String name;
    private Long managerId;
    private String managerName;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name, Long managerId, String managerName) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.managerName = managerName;
    }

}