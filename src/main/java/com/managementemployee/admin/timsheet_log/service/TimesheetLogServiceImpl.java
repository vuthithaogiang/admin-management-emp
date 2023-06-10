package com.managementemployee.admin.timsheet_log.service;


import com.managementemployee.admin.timsheet_log.model.TimesheetLog;
import com.managementemployee.admin.timsheet_log.repository.TimesheetLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetLogServiceImpl implements TimesheetLogService {

    @Autowired
    private TimesheetLogRepository timesheetLogRepository;

    @Override
    public List<TimesheetLog> getAll() {
        return timesheetLogRepository.findAll();
    }
}
