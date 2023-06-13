package com.managementemployee.admin.timesheet_details.service;

import com.managementemployee.admin.timesheet_details.model.TimesheetDetailsView;

import java.util.List;

public interface TimesheetDetailsViewService {

    public List<TimesheetDetailsView> findAllTimesheetDetails();

    public List<TimesheetDetailsView> findAllTimesheetDetailsInToday();

    public List<TimesheetDetailsView> findAllTimesheetDetailInThisWeek();

    public List<TimesheetDetailsView> findAllByLastWeek();

    public List<TimesheetDetailsView> findAllTimesheetDetailsInThisMoth();

    public List<TimesheetDetailsView> findAllTimesheetDetailsByEmpId(Integer empId);

    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdInWeek(Integer empId);

    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdLastWeek(Integer empId);

    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdToday(Integer empId);


}