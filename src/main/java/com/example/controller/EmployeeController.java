package com.example.controller;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession session;

    @GetMapping("list")
    public String list() {
        session.setAttribute("admin",session.getAttribute("admin"));
        session.setAttribute("employeeList", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("showList")
    public String showList() {
        return "redirect:list";
    }

    @GetMapping("detail")
    public String detail(Integer id) {
        session.setAttribute("employee",employeeService.findById(id));
        return "employee/detail";
    }

    @PostMapping("detail")
    public String updateDetail(Employee employee){
        employeeService.update(employee);
        return "redirect:/employee/detail?id=" + employee.getId();
    }


}
