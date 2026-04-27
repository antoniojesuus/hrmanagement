package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DepartmentProjectDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private Long departmentId;
    private String departmentName;

    public DepartmentProjectDTO() {
    }

    public DepartmentProjectDTO(Long id, String name, String description,
                                LocalDate startDate, LocalDate endDate,
                                ProjectStatus status, Long departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

}