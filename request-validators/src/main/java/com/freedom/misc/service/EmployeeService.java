package com.freedom.misc.service;

import com.freedom.misc.request.AddEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    public void addEmployee(AddEmployeeRequest addEmployeeRequest, String requestId) {

        log.info("Employee Added. requestId:{}, addEmployeeRequest: {}" + requestId + ":" + addEmployeeRequest);
    }
}