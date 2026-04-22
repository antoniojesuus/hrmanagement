package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.PayrollCycle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PayrollDTO {

    private Long id;
    private LocalDate payDate;
    private Double baseSalary;
    private PayrollCycle cycle;
    private Double bonus;
    private Double deductions;
    private Double tax;
    private Double netSalary;
    private Long employeeId;
    private String employeeName;

    public PayrollDTO() {
    }

    public PayrollDTO(Long id, LocalDate payDate, Double baseSalary, PayrollCycle cycle, Double bonus,
                      Double deductions, Double tax, Double netSalary,
                      Long employeeId, String employeeName) {
        this.id = id;
        this.payDate = payDate;
        this.baseSalary = baseSalary;
        this.cycle = cycle;
        this.bonus = bonus;
        this.deductions = deductions;
        this.tax = tax;
        this.netSalary = netSalary;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

}