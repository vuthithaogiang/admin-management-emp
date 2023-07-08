package com.managementemployee.admin.timesheet.repository;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.timesheet.model.Timesheet;

import org.hibernate.annotations.Subselect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {


    List<Timesheet> findByEmployee(Employee employee);

    List<Timesheet> findAllByStatus(int status);

    List <Timesheet> findAllByTrash(int trash);


    @Query( value = "SELECT * FROM timesheet as t WHERE  t.emp_id = ?1 and MONTH(t.date_in) = ?2 and t.status = 1 and trash = 0",  nativeQuery = true)
    List<Timesheet> findAllByEmpIdAndMoth(Integer empId, Integer month);

}
