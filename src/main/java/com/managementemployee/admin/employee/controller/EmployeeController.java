package com.managementemployee.admin.employee.controller;

import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public String add(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return "New employee is added!";
    }


    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
        return employeeService.getStudentByName(name);
    }

    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) throws InvalidEmailException {
        return employeeService.updateEmployee(employee);
    }

    @PutMapping("delete/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployeeById(id);
    }
}
