package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.CreateEmployeeRequest;
import com.ntt.hrmanagement.dto.EmployeeDTO;
import com.ntt.hrmanagement.dto.UpdateEmployeeRequest;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.model.Employee;
import com.ntt.hrmanagement.model.EmployeeStatus;
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

    public List<EmployeeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public EmployeeDTO getById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        return mapToDto(employee);
    }

    public EmployeeDTO save(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        employee.setStatus(EmployeeStatus.ACTIVE);

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Departamento no encontrado con id: " + request.getDepartmentId()));
            employee.setDepartment(department);
        }

        if (employee.getTotalVacationDays() == null) {
            employee.setTotalVacationDays(22);
        }

        if (employee.getAvailableVacationDays() == null) {
            employee.setAvailableVacationDays(employee.getTotalVacationDays());
        }

        Employee savedEmployee = repository.save(employee);
        return mapToDto(savedEmployee);
    }

    public EmployeeDTO update(Long id, UpdateEmployeeRequest request) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Departamento no encontrado con id: " + request.getDepartmentId()));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        Employee updatedEmployee = repository.save(employee);
        return mapToDto(updatedEmployee);
    }

    public EmployeeDTO updateStatus(Long id, EmployeeStatus status) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        employee.setStatus(status);

        return mapToDto(repository.save(employee));
    }

    public void delete(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        repository.delete(employee);
    }

    public List<EmployeeDTO> getByDepartmentId(Long departmentId) {
        return repository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Integer getAvailableVacationDays(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        return employee.getAvailableVacationDays();
    }

    private EmployeeDTO mapToDto(Employee employee) {
        String departmentName = null;

        if (employee.getDepartment() != null) {
            departmentName = employee.getDepartment().getName();
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getStatus(),
                departmentName
        );
    }

}