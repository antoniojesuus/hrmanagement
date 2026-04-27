package com.ntt.hrmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDepartmentProjectRequest {

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "El departmentId es obligatorio")
    private Long departmentId;

}