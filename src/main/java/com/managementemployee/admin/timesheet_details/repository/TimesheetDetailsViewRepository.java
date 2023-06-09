package com.managementemployee.admin.timesheet_details.repository;

import com.managementemployee.admin.timesheet_details.model.TimesheetDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TimesheetDetailsViewRepository
        extends JpaRepository<TimesheetDetailsView, String> {


}
