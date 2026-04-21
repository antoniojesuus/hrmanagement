package com.ntt.hrmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateLeaveRequest {

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate endDate;

    @NotBlank(message = "El motivo es obligatorio")
    private String reason;

    @NotNull(message = "El employeeId es obligatorio")
    private Long employeeId;

}