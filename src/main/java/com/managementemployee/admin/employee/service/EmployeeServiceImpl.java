package com.managementemployee.admin.employee.service;

import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.common.exception.InvalidPasswordException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.model.EmployeeAvatarOnly;
import com.managementemployee.admin.employee.model.EmployeeEmailOnly;
import com.managementemployee.admin.employee.model.EmployeeLogin;
import com.managementemployee.admin.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public String setDefaultPassWord() throws InvalidPasswordException {
        String defaultPassword = "123Abc!";
        for(var item : employeeRepository.findAll()){
            item.setPassword(defaultPassword);
            employeeRepository.save(item);
        }
        return "Set default password complete!";
    }
    @Override
    public String authenticateEmployee(EmployeeLogin employee) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Employee optionalEmployee = employeeRepository.findById(employee.getEmpId()).orElse(null);

        if(optionalEmployee != null) {
            if(bCryptPasswordEncoder.matches(employee.getPassword(), optionalEmployee.getPassword()) ) {
                return "Authenticated Employee";
            }
            else{
                return  "Incorrect Password";
            }
        }

        return "No employee is not found for this employee!";
    }


    @Override
    public String authenticateEmployeeAdmin(EmployeeLogin employeeLogin) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Employee optionalEmployee = employeeRepository.findById(employeeLogin.getEmpId()).orElse(null);

        if(optionalEmployee != null) {
            if(bCryptPasswordEncoder.matches(employeeLogin.getPassword(), optionalEmployee.getPassword())
                    && optionalEmployee.getTypeOfEmployee().matches("admin")) {
                return "Authenticated Admin";
            }
            else{
                return  "Incorrect Admin";
            }
        }

        return "No admin employee is not found for this employee!";
    }

    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email);
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


    @Override
    public Employee updateAvatar(Integer empId, EmployeeAvatarOnly employeeAvatarOnly) {

        Employee employeeExisting = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid emp id" + empId));
        employeeExisting.setAvatar(employeeAvatarOnly.getAvatar());
        return employeeRepository.save(employeeExisting);
    }

    @Override
    public Employee updateEmail(Integer empId, EmployeeEmailOnly employeeEmailOnly) {
        Employee employeeExisting = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid emp id" + empId));

        try{
            employeeExisting.setEmail(employeeEmailOnly.getEmail());
            return employeeRepository.save(employeeExisting);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
