package com.ntt.hrmanagement.service;

import com.ntt.hrmanagement.dto.CreateDepartmentProjectRequest;
import com.ntt.hrmanagement.dto.DepartmentProjectDTO;
import com.ntt.hrmanagement.exception.ResourceNotFoundException;
import com.ntt.hrmanagement.model.Department;
import com.ntt.hrmanagement.model.DepartmentProject;
import com.ntt.hrmanagement.model.ProjectStatus;
import com.ntt.hrmanagement.repository.DepartmentProjectRepository;
import com.ntt.hrmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentProjectService {

    private final DepartmentProjectRepository repository;
    private final DepartmentRepository departmentRepository;

    public DepartmentProjectService(DepartmentProjectRepository repository,
                                    DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentProjectDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public DepartmentProjectDTO getById(Long id) {
        DepartmentProject project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id: " + id));

        return mapToDto(project);
    }

    public List<DepartmentProjectDTO> getByDepartmentId(Long departmentId) {
        return repository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public DepartmentProjectDTO create(CreateDepartmentProjectRequest request) {
        if (request.getEndDate() != null && request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Departamento no encontrado con id: " + request.getDepartmentId()));

        DepartmentProject project = new DepartmentProject();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setStatus(ProjectStatus.PLANNED);
        project.setDepartment(department);

        DepartmentProject savedProject = repository.save(project);
        return mapToDto(savedProject);
    }

    private DepartmentProjectDTO mapToDto(DepartmentProject project) {
        Long departmentId = null;
        String departmentName = null;

        if (project.getDepartment() != null) {
            departmentId = project.getDepartment().getId();
            departmentName = project.getDepartment().getName();
        }

        return new DepartmentProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                departmentId,
                departmentName
        );
    }
}