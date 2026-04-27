package com.ntt.hrmanagement.repository;

import com.ntt.hrmanagement.model.DepartmentProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentProjectRepository extends JpaRepository<DepartmentProject, Long> {

    List<DepartmentProject> findByDepartmentId(Long departmentId);
}