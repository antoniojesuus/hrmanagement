package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + id));
    }

    public Department save(Department department) {
        return repository.save(department);
    }

    public Department update(Long id, Department departmentData) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + id));

        department.setName(departmentData.getName());

        return repository.save(department);
    }

    public void delete(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + id));

        repository.delete(department);
    }
}