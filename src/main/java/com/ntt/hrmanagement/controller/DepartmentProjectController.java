package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.dto.CreateDepartmentProjectRequest;
import com.ntt.hrmanagement.dto.DepartmentProjectDTO;
import com.ntt.hrmanagement.service.DepartmentProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department-projects")
public class DepartmentProjectController {

    private final DepartmentProjectService service;

    public DepartmentProjectController(DepartmentProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<DepartmentProjectDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentProjectDTO getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<DepartmentProjectDTO> getByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        return service.getByDepartmentId(departmentId);
    }

    @PostMapping
    public DepartmentProjectDTO create(@Valid @RequestBody CreateDepartmentProjectRequest request) {
        return service.create(request);
    }
}