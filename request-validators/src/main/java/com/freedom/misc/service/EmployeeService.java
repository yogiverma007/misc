package com.freedom.misc.service;

import com.freedom.misc.request.AddEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public void addEmployee(AddEmployeeRequest addEmployeeRequest, String requestId) {

        System.out.println("Employee Added......");
    }
}