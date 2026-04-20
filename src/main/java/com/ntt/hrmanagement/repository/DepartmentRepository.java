package com.ntt.hrmanagement.repository;

import com.ntt.hrmanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}