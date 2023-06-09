package com.managementemployee.admin.employee_off.service;


import com.managementemployee.admin.employee_off.model.EmployeeOffView;
import com.managementemployee.admin.employee_off.repository.EmployeeOffViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeOffViewServiceImpl implements EmployeeOffViewService {

    @Autowired
    private EmployeeOffViewRepository employeeOffViewRepository;

    @Override
    public List<EmployeeOffView> findAll() {
        return employeeOffViewRepository.findAll();
    }

    @Override
    public List<EmployeeOffView> findAllByEmpId(Integer empId) {
        List<EmployeeOffView> list = employeeOffViewRepository.findAll();

        List<EmployeeOffView> result = new ArrayList<>();

        for(var item : list){
            if(item.getEmpId() == empId){
                result.add(item);
            }
        }
        return result;
    }
}
