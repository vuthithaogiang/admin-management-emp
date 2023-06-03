package com.managementemployee.admin.timesheet.service;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.timesheet.model.Timesheet;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface TimesheetService {

    public Timesheet saveTimesheet(int empId);

    public List<Timesheet> getAllTimesheet();


    public List<Timesheet> getTimesheetByEmployeeId(int empId) ;

    public Timesheet getTimesheetByEmployeeIdAndDateNow(int empId);

    public List<Timesheet> getTimesheetByStatus(int status);

}
