package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.LeaveStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LeaveRequestDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
    private Long employeeId;
    private String employeeName;

    public LeaveRequestDTO() {
    }

    public LeaveRequestDTO(Long id, LocalDate startDate, LocalDate endDate, String reason,
                           LeaveStatus status, Long employeeId, String employeeName) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

}
