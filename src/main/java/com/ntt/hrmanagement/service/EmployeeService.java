package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.EmployeeDto;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.model.Employee;
import com.ntt.hrmanagement.repository.DepartmentRepository;
import com.ntt.hrmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public EmployeeDto getById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        return mapToDto(employee);
    }

    public Employee save(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Departamento no encontrado con id: " + employee.getDepartment().getId()));

            employee.setDepartment(department);
        }

        return repository.save(employee);
    }

    public Employee update(Long id, Employee employeeData) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        employee.setName(employeeData.getName());
        employee.setPosition(employeeData.getPosition());
        employee.setSalary(employeeData.getSalary());

        if (employeeData.getDepartment() != null && employeeData.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(employeeData.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Departamento no encontrado con id: " + employeeData.getDepartment().getId()));

            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        return repository.save(employee);
    }

    public void delete(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        repository.delete(employee);
    }

    public List<Employee> getByDepartmentId(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }

    private EmployeeDto mapToDto(Employee employee) {
        String departmentName = employee.getDepartment() != null
                ? employee.getDepartment().getName()
                : null;

        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSalary(),
                departmentName
        );
    }
}