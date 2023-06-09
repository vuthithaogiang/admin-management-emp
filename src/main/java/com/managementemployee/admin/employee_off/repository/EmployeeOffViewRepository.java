package com.managementemployee.admin.employee_off.repository;

import com.managementemployee.admin.employee_off.model.EmployeeOffView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOffViewRepository extends JpaRepository<EmployeeOffView, String> {
}
