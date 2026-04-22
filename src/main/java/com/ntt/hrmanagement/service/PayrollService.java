package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.CreatePayrollRequest;
import com.ntt.hrmanagement.dto.PayrollDTO;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Employee;
import com.ntt.hrmanagement.model.Payroll;
import com.ntt.hrmanagement.model.PayrollCycle;
import com.ntt.hrmanagement.repository.EmployeeRepository;
import com.ntt.hrmanagement.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository repository;
    private final EmployeeRepository employeeRepository;

    public PayrollService(PayrollRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    public List<PayrollDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public PayrollDTO getById(Long id) {
        Payroll payroll = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nómina no encontrada con id: " + id));

        return mapToDto(payroll);
    }

    public List<PayrollDTO> getByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public PayrollDTO create(CreatePayrollRequest request) {
        if (request.getBonus() != null && request.getBonus() < 0) {
            throw new IllegalArgumentException("El bonus no puede ser negativo");
        }

        if (request.getDeductions() != null && request.getDeductions() < 0) {
            throw new IllegalArgumentException("Las deducciones no pueden ser negativas");
        }

        if (request.getTax() != null && request.getTax() < 0) {
            throw new IllegalArgumentException("Los impuestos no pueden ser negativos");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empleado no encontrado con id: " + request.getEmployeeId()));

        Payroll payroll = new Payroll();
        payroll.setPayDate(request.getPayDate());
        payroll.setBaseSalary(request.getBaseSalary() != null ? request.getBaseSalary() : employee.getSalary());
        payroll.setCycle(request.getCycle());
        payroll.setBonus(request.getBonus() != null ? request.getBonus() : 0.0);
        payroll.setDeductions(request.getDeductions() != null ? request.getDeductions() : 0.0);
        payroll.setTax(request.getTax() != null ? request.getTax() : 0.0);
        payroll.setEmployee(employee);

        double netSalary = payroll.getBaseSalary()
                + payroll.getBonus()
                - payroll.getDeductions()
                - payroll.getTax();

        payroll.setNetSalary(netSalary);

        if (netSalary < 0) {
            throw new IllegalArgumentException("El salario neto no puede ser negativo");
        }

        Payroll saved = repository.save(payroll);
        return mapToDto(saved);
    }

    public Double getTotalCost() {
        return repository.findAll()
                .stream()
                .mapToDouble(Payroll::getNetSalary)
                .sum();
    }

    public List<PayrollDTO> getByCycle(PayrollCycle cycle) {
        return repository.findByCycle(cycle)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private PayrollDTO mapToDto(Payroll payroll) {
        Long employeeId = null;
        String employeeName = null;

        if (payroll.getEmployee() != null) {
            employeeId = payroll.getEmployee().getId();
            employeeName = payroll.getEmployee().getName();
        }

        return new PayrollDTO(
                payroll.getId(),
                payroll.getPayDate(),
                payroll.getBaseSalary(),
                payroll.getCycle(),
                payroll.getBonus(),
                payroll.getDeductions(),
                payroll.getTax(),
                payroll.getNetSalary(),
                employeeId,
                employeeName
        );
    }
}