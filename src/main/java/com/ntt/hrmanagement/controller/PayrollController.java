package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.dto.CreatePayrollRequest;
import com.ntt.hrmanagement.dto.PayrollDTO;
import com.ntt.hrmanagement.model.PayrollCycle;
import com.ntt.hrmanagement.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payrolls")
public class PayrollController {

    private final PayrollService service;

    public PayrollController(PayrollService service) {
        this.service = service;
    }

    @GetMapping
    public List<PayrollDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PayrollDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<PayrollDTO> getByEmployeeId(@PathVariable Long employeeId) {
        return service.getByEmployeeId(employeeId);
    }

    @GetMapping("/total-cost")
    public Double getTotalCost() {
        return service.getTotalCost();
    }

    @GetMapping("/cycle/{cycle}")
    public List<PayrollDTO> getByCycle(@PathVariable PayrollCycle cycle) {
        return service.getByCycle(cycle);
    }

    @PostMapping
    public PayrollDTO create(@Valid @RequestBody CreatePayrollRequest request) {
        return service.create(request);
    }
}