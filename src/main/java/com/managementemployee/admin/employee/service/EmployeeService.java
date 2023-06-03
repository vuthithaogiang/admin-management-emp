package com.managementemployee.admin.employee.service;

import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.employee.model.Employee;


import java.util.List;


public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public List<Employee> getStudentByName(String name);

    public Employee getEmployeeById(int id);

    public String deleteEmployeeById(int id);

    public Employee updateEmployee(Employee employee) throws InvalidEmailException;

}
