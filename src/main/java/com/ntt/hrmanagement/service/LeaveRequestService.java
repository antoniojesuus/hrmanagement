package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.CreateLeaveRequest;
import com.ntt.hrmanagement.dto.LeaveRequestDTO;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Employee;
import com.ntt.hrmanagement.model.LeaveRequest;
import com.ntt.hrmanagement.model.LeaveStatus;
import com.ntt.hrmanagement.repository.EmployeeRepository;
import com.ntt.hrmanagement.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository repository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(LeaveRequestRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    public List<LeaveRequestDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public LeaveRequestDTO getById(Long id) {
        LeaveRequest leaveRequest = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        return mapToDto(leaveRequest);
    }

    public List<LeaveRequestDTO> getByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public LeaveRequestDTO create(CreateLeaveRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empleado no encontrado con id: " + request.getEmployeeId()));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequest.setReason(request.getReason());
        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setEmployee(employee);

        LeaveRequest saved = repository.save(leaveRequest);
        return mapToDto(saved);
    }

    public LeaveRequestDTO approve(Long id) {
        LeaveRequest leaveRequest = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalArgumentException("Solo se pueden aprobar solicitudes en estado PENDING");
        }

        leaveRequest.setStatus(LeaveStatus.APPROVED);
        return mapToDto(repository.save(leaveRequest));
    }

    public LeaveRequestDTO reject(Long id) {
        LeaveRequest leaveRequest = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalArgumentException("Solo se pueden rechazar solicitudes en estado PENDING");
        }

        leaveRequest.setStatus(LeaveStatus.REJECTED);
        return mapToDto(repository.save(leaveRequest));
    }

    private LeaveRequestDTO mapToDto(LeaveRequest leaveRequest) {
        Long employeeId = null;
        String employeeName = null;

        if (leaveRequest.getEmployee() != null) {
            employeeId = leaveRequest.getEmployee().getId();
            employeeName = leaveRequest.getEmployee().getName();
        }

        return new LeaveRequestDTO(
                leaveRequest.getId(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getReason(),
                leaveRequest.getStatus(),
                employeeId,
                employeeName
        );
    }
}