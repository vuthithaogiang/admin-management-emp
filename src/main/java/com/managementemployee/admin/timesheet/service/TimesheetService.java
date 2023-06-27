package com.managementemployee.admin.timesheet.service;

import com.managementemployee.admin.common.exception.InvalidTimeInException;
import com.managementemployee.admin.common.exception.InvalidTimeOutException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.timesheet.model.Timesheet;



import java.util.List;

public interface TimesheetService {

    public Timesheet saveTimesheet(int empId) throws InvalidTimeOutException, InvalidTimeInException;

    public List<Timesheet> getAllTimesheet();

    public List<Timesheet> getAllInTrash();

    public List<Timesheet> getTimesheetByEmployeeId(int empId) ;

    public Timesheet getTimesheetByEmployeeIdAndDateNow(int empId);

    public List<Timesheet> getTimesheetByStatus(int status);

    public List<Timesheet> saveDefaultEmpAcceptedFurloughAbsent();

    public Timesheet saveToTrash(Integer timesheetId);

    public Timesheet toRestoreFromTrash(Integer timesheetId);

    public String deleteTimesheet(Integer timesheetId);

    public Timesheet editTimesheet(Timesheet timesheet);

}
