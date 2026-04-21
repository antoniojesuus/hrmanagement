package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.dto.CreateLeaveRequest;
import com.ntt.hrmanagement.dto.LeaveRequestDTO;
import com.ntt.hrmanagement.service.LeaveRequestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeaveRequestDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LeaveRequestDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequestDTO> getByEmployeeId(@PathVariable Long employeeId) {
        return service.getByEmployeeId(employeeId);
    }

    @PostMapping
    public LeaveRequestDTO create(@Valid @RequestBody CreateLeaveRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequestDTO approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequestDTO reject(@PathVariable Long id) {
        return service.reject(id);
    }
}