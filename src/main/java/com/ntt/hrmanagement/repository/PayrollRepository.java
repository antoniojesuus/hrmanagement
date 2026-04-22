package com.ntt.hrmanagement.repository;

import com.ntt.hrmanagement.model.Payroll;
import com.ntt.hrmanagement.model.PayrollCycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeId(Long employeeId);

    List<Payroll> findByCycle(PayrollCycle cycle);
}