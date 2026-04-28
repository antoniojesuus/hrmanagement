package com.ntt.hrmanagement.controller;

import com.ntt.hrmanagement.service.DepartmentService;
import com.ntt.hrmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public WebController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/employees-view")
    public String employeesView(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employees";
    }

    @GetMapping("/departments-view")
    public String departmentsView(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "departments";
    }
}