package com.freedom.misc.controller;

import com.freedom.misc.request.AddEmployeeRequest;
import com.freedom.misc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/v1/add")
    public String getBalance(@RequestBody @Validated AddEmployeeRequest request,
                             @RequestParam String requestId) {

        employeeService.addEmployee(request, requestId);

        return "";
    }

}