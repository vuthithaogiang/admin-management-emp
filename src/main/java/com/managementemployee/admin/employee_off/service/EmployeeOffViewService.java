package com.managementemployee.admin.employee_off.service;

import com.managementemployee.admin.employee_off.model.EmployeeOffView;

import java.util.List;

public interface EmployeeOffViewService {

    public List<EmployeeOffView> findAll();

    public List<EmployeeOffView> findAllByEmpId(Integer empId);
}
