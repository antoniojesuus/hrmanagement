package com.ntt.hrmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate payDate;

    @NotNull(message = "El salario base es obligatorio")
    private Double baseSalary;

    @Enumerated(EnumType.STRING)
    private PayrollCycle cycle;

    private Double bonus;

    private Double deductions;

    private Double tax;

    private Double netSalary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}