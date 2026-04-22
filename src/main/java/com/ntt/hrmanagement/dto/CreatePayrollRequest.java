package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.PayrollCycle;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CreatePayrollRequest {

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate payDate;

    private Double baseSalary;

    @NotNull(message = "El ciclo de pago es obligatorio")
    private PayrollCycle cycle;

    private Double bonus;

    private Double deductions;

    private Double tax;

    @NotNull(message = "El employeeId es obligatorio")
    private Long employeeId;

}