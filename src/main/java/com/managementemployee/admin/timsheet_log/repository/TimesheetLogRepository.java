package com.managementemployee.admin.timsheet_log.repository;


import com.managementemployee.admin.timsheet_log.model.TimesheetLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetLogRepository extends JpaRepository<TimesheetLog, String> {
}
