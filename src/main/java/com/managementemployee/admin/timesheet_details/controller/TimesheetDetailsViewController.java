package com.managementemployee.admin.timesheet_details.controller;


import com.managementemployee.admin.timesheet_details.model.TimesheetDetailsView;
import com.managementemployee.admin.timesheet_details.service.TimesheetDetailsViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheetDetails")
@CrossOrigin(origins = "*")
public class TimesheetDetailsViewController {

    @Autowired
    private TimesheetDetailsViewService timesheetDetailsViewService;

    @GetMapping("/getAll")
    public List<TimesheetDetailsView> getAll() {
        return timesheetDetailsViewService.findAllTimesheetDetails();
    }

    @GetMapping("/getAllByEmpId/{empId}")
    public List<TimesheetDetailsView> getAllByEmpId(@PathVariable Integer empId) {
        return timesheetDetailsViewService.findAllTimesheetDetailsByEmpId(empId);
    }

    @GetMapping("/getAllByEmpIdOnWeek/{empId}")
    public List<TimesheetDetailsView> getAllByEmpIdOnWeek(@PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdInWeek(empId);
    }

    @GetMapping("/getAllByEmpIdToday/{empId}")
    public List<TimesheetDetailsView> getAllByEmpIdToday(@PathVariable Integer empId){
        return timesheetDetailsViewService.findAllTimesheetDetailsViewByEmpIdToday(empId);
    }
}
