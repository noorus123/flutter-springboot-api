package com.mongodb.controller;

import com.mongodb.exception.ResourceNotFoundException;
import com.mongodb.model.Employee;
import com.mongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empData")
public class EmployeeControleller {
	@Autowired
    EmployeeRepository employeeRepository;

	//http://localhost:8080/empData/employees
    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    //http://localhost:8080/empData/employee
    @RequestMapping(value="/employee", method = RequestMethod.POST) 
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //http://localhost:8080/empData/employee/4
    @RequestMapping(value="/employee/{empId}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable(value = "empId") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", employeeId));
    }

    //http://localhost:8080/empData/employee/4
    @RequestMapping(value="/employee/{empId}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable(value = "empId") Long employeeId, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", employeeId));
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setSalary(employeeDetails.getSalary());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }
   
    //http://localhost:8080/empData/employee/4
    @RequestMapping(value="/employee/{empId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "empId") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", employeeId));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }

}
