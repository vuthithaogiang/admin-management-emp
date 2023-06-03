package com.managementemployee.admin.timesheet.controller;

import com.managementemployee.admin.timesheet.model.Timesheet;
import com.managementemployee.admin.timesheet.service.TimesheetService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {

    @Autowired
    private TimesheetService timesheetService;

    //timesheet?empId={number}
    @PostMapping
    public ResponseEntity<Timesheet> addTimesheet(
            @RequestParam int empId) {
        Timesheet timesheet = timesheetService.saveTimesheet(empId);
        return new ResponseEntity<>(timesheet, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Timesheet> getAllTimesheet(){
        return timesheetService.getAllTimesheet();
    }

    @GetMapping("getAllByEmp/{empId}")
    public List<Timesheet> getAllByEmpId(@PathVariable int empId){
        return timesheetService.getTimesheetByEmployeeId(empId);

    }

    @GetMapping("getAllByEmpToday/{empId}")
    public Timesheet getAllByEmpIdAndToday(@PathVariable int empId){
        return timesheetService.getTimesheetByEmployeeIdAndDateNow(empId);
    }

    @GetMapping("/getAllByStatus/{status}")
    public List<Timesheet> getAllByStatus(@PathVariable int status){
        return timesheetService.getTimesheetByStatus(status);
    }




}
