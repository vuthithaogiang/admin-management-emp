package com.managementemployee.admin.timesheet.service;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.repository.EmployeeRepository;
import com.managementemployee.admin.timesheet.model.Timesheet;
import com.managementemployee.admin.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetServiceImpl implements TimesheetService {
    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Timesheet saveTimesheet( int empId) {

       int sizeOfEmpPresent = getTimesheetByEmployeeId(empId).size();
       var EmpPresentToday = getTimesheetByEmployeeIdAndDateNow(empId);
        if(sizeOfEmpPresent == 0 || EmpPresentToday == null) {
            Employee employee = employeeRepository.findById(empId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));


            Timesheet timesheet = new Timesheet();
            timesheet.setEmployee(employee);
            //timesheet.setDateIn(LocalDate.now());
            timesheet.setTimeIn(LocalDateTime.now());
            timesheet.setStatus(1);

            return timesheetRepository.save(timesheet);

        }
        else{
            Timesheet timesheet = getTimesheetByEmployeeIdAndDateNow(empId);
            Duration duration = Duration.between(  timesheet.getTimeIn(), LocalDateTime.now());



            System.out.println(duration.toMinutes());

            // gia su check out success neu da lm dc it nhat 1 shift: 4hours
            if(duration.toMinutes() > 60*4){
                timesheet.setTimeOut(LocalDateTime.now());
            }

            return timesheet;
        }


    }
    @Override
    public List<Timesheet> getAllTimesheet() {
        return timesheetRepository.findAll();
    }

    @Override
    public List<Timesheet> getTimesheetByEmployeeId(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));

        return timesheetRepository.findByEmployee(employee);
    }

    @Override
    public Timesheet getTimesheetByEmployeeIdAndDateNow(int empId) {
        List<Timesheet> timesheetList  = getTimesheetByEmployeeId(empId);

        if(timesheetList.size() > 0 ){
            for(var item: timesheetList){
                if(item.getDateIn().isEqual(LocalDate.now())){
                    return item;
                }
            }
        }
         return null;
    }

    @Override
    public List<Timesheet> getTimesheetByStatus(int status){
        return timesheetRepository.findAllByStatus(status);
    }


}
