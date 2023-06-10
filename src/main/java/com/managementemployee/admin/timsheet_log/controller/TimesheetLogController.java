package com.managementemployee.admin.timsheet_log.controller;

import com.managementemployee.admin.timsheet_log.model.TimesheetLog;
import com.managementemployee.admin.timsheet_log.service.TimesheetLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
@RequestMapping("/timesheetLog")
@CrossOrigin(origins = "*")
public class TimesheetLogController {
    @Autowired
    private TimesheetLogService timesheetLogService;

    @GetMapping("/getAll")
    public List<TimesheetLog> getAll() {
        return  timesheetLogService.getAll();
    }

    @GetMapping("/getToday")
    public List<TimesheetLog> getToday() {
        return timesheetLogService.getToday();
    }

}
