package com.managementemployee.admin.timesheet.repository;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {


    List<Timesheet> findByEmployee(Employee employee);


    List<Timesheet> findAllByStatus(int status);

    List <Timesheet> findAllByTrash(int trash);

}
