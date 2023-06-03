package com.managementemployee.admin.employee.service;

import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getStudentByName(String name) {

       List<Employee> employeeList = new ArrayList<>();


        employeeList.addAll(employeeRepository.findByFirstName(name)) ;
        employeeList.addAll(employeeRepository.findByLastName(name)) ;
        employeeList.addAll(employeeRepository.findByMidName(name)) ;

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteEmployeeById(int id) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if(existingEmployee == null) {
            return "null";
        }
        else{
            existingEmployee.setStatus("DELETED");
            employeeRepository.save(existingEmployee);
            return "Employee is removed!" + id;
        }

    }

    @Override
    public Employee updateEmployee(Employee employee) throws InvalidEmailException {
      Employee existingEmployee = employeeRepository.findById(employee.getEmpIdInt()).orElse(null);

      existingEmployee.setFirstName(employee.getFirstName());
      existingEmployee.setMidName(employee.getMidName());
      existingEmployee.setLastName((employee.getLastName()));
      try {
          existingEmployee.setEmail(employee.getEmail());
      }
      catch (Exception e) {
         throw new InvalidEmailException("Invalid email", employee.getEmail());
      }
       existingEmployee.setAvatar(employee.getAvatar());
      return employeeRepository.save(existingEmployee);
    }
}
