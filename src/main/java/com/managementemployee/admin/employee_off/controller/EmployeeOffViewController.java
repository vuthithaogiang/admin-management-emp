package com.managementemployee.admin.employee_off.controller;

import com.managementemployee.admin.employee_off.model.EmployeeOffView;
import com.managementemployee.admin.employee_off.repository.EmployeeOffViewRepository;
import com.managementemployee.admin.employee_off.service.EmployeeOffViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeOff")
@CrossOrigin(origins = "*")
public class EmployeeOffViewController {

    @Autowired
    private EmployeeOffViewService employeeOffViewService;

    @GetMapping("/getAll")
    public List<EmployeeOffView> getAll() {
        return employeeOffViewService.findAll();
    }


    @GetMapping("/getAllByEmpId/{empId}")
    public List<EmployeeOffView> getAllByEmpId(@PathVariable Integer empId){
        return employeeOffViewService.findAllByEmpId(empId);
    }
}
