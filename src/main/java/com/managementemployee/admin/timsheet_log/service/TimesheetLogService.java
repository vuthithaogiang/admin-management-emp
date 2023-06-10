package com.managementemployee.admin.timsheet_log.service;

import com.managementemployee.admin.timsheet_log.model.TimesheetLog;

import java.util.List;

public interface TimesheetLogService  {
    public List<TimesheetLog> getAll();

    public List<TimesheetLog> getToday();
}
