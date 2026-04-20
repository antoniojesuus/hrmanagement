package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.dto.CreateEmployeeRequest;
import com.ntt.hrmanagement.dto.EmployeeDTO;
import com.ntt.hrmanagement.dto.UpdateEmployeeRequest;
import com.ntt.hrmanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}