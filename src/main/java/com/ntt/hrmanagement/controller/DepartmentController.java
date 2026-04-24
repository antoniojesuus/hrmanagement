package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.dto.DepartmentDTO;
import com.ntt.hrmanagement.dto.EmployeeDTO;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.service.DepartmentService;
import com.ntt.hrmanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService service, EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<DepartmentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Department create(@Valid @RequestBody Department department) {
        return service.save(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @Valid @RequestBody Department department) {
        return service.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable Long id) {
        return employeeService.getByDepartmentId(id);
    }

    @PutMapping("/{id}/manager")
    public DepartmentDTO assignManager(@PathVariable("id") Long id,
                                       @RequestParam("managerId") Long managerId) {
        return service.assignManager(id, managerId);
    }
}