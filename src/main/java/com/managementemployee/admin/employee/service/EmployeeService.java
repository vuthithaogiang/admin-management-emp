package com.managementemployee.admin.employee.service;

import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.common.exception.InvalidPasswordException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.model.EmployeeAvatarOnly;
import com.managementemployee.admin.employee.model.EmployeeEmailOnly;
import com.managementemployee.admin.employee.model.EmployeeLogin;


import java.util.List;


public interface EmployeeService {

    public Employee saveEmployee(Employee employee);


    public String authenticateEmployee(EmployeeLogin employee);

    public String authenticateEmployeeAdmin(EmployeeLogin employeeLogin);

    public List<Employee> getAllEmployees();

    public List<Employee> getStudentByName(String name);

    public Employee getEmployeeById(int id);

    public String deleteEmployeeById(int id);

    public Employee updateEmployee(Employee employee) throws InvalidEmailException;

    public Employee updateAvatar(Integer empId, EmployeeAvatarOnly employeeAvatarOnly);

    public Employee updateEmail(Integer empId, EmployeeEmailOnly employeeEmailOnly);

    String setDefaultPassWord() throws InvalidPasswordException;

}
