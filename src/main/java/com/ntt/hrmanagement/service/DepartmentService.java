package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.DepartmentDTO;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.model.Employee;
import com.ntt.hrmanagement.repository.DepartmentRepository;
import com.ntt.hrmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public DepartmentDTO getById(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + id));

        return mapToDto(department);
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

    public DepartmentDTO assignManager(Long departmentId, Long managerId) {
        Department department = repository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + departmentId));

        Employee manager = employeeRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + managerId));

        department.setManager(manager);

        Department updatedDepartment = repository.save(department);
        return mapToDto(updatedDepartment);
    }

    private DepartmentDTO mapToDto(Department department) {
        Long managerId = null;
        String managerName = null;

        if (department.getManager() != null) {
            managerId = department.getManager().getId();
            managerName = department.getManager().getName();
        }

        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                managerId,
                managerName
        );
    }
}