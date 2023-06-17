package com.managementemployee.admin.timesheet_details.controller;


import com.managementemployee.admin.common.entity.DateDefinition;
import com.managementemployee.admin.timesheet_details.model.TimesheetDetailsView;
import com.managementemployee.admin.timesheet_details.service.TimesheetDetailsViewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/timesheetDetails")
@CrossOrigin(origins = "*")
public class TimesheetDetailsViewController {

    @Autowired
    private TimesheetDetailsViewService timesheetDetailsViewService;

    @GetMapping("/getAll/all")
    public List<TimesheetDetailsView> getAll() {
        return timesheetDetailsViewService.findAllTimesheetDetails();
    }


    @GetMapping("/getAll/today")
    public List<TimesheetDetailsView> getAllToday(){
        return timesheetDetailsViewService.findAllTimesheetDetailsInToday();
    }


    @GetMapping("/getAll/thisWeek")
    public List<TimesheetDetailsView> getAllThisWeek() {
        return timesheetDetailsViewService.findAllTimesheetDetailInThisWeek();
    }


    @GetMapping("/getAll/lastWeek")
    public List<TimesheetDetailsView> getAllLastWeek() {
        return timesheetDetailsViewService.findAllByLastWeek();
    }
    @GetMapping("/getAll/thisMonth")
    public List<TimesheetDetailsView> getAllThisMonth() {
        return timesheetDetailsViewService.findAllTimesheetDetailsInThisMoth();
    }
    @GetMapping("/getAllByEmpId/{empId}/all")
    public List<TimesheetDetailsView> getAllByEmpId(@PathVariable Integer empId) {
        return timesheetDetailsViewService.findAllTimesheetDetailsByEmpId(empId);
    }

    @GetMapping("/getAllByEmpId/{empId}/thisWeek")
    public List<TimesheetDetailsView> getAllByEmpIdOnWeek(@PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdInWeek(empId);
    }

    @GetMapping("/getAllByEmpId/{empId}/today")
    public List<TimesheetDetailsView> getAllByEmpIdToday(@PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdToday(empId);
    }

    @GetMapping("/getAllByEmpId/{empId}/lastWeek")
    public List<TimesheetDetailsView> getAllByEmpIdLastWeek(@PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdLastWeek(empId);
    }


    @PostMapping("/getAll/dueDate")

    public List<TimesheetDetailsView> getAllFromDateToOtherDate(@RequestBody DateDefinition date){
        return  timesheetDetailsViewService.findAllTimeSheetDetailsViewFromDateToOtherDate(date);
    }

    @PostMapping("/getAllByEmpId/{empId}/dueDate")
    public List<TimesheetDetailsView> getAllByEmpIdFromDateToOtherDate(@RequestBody DateDefinition date,
                                                                       @PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdFromDateToOtherDate(date, empId);
    }
}
