package com.managementemployee.admin.timsheet_log.model;


import jakarta.persistence.*;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Immutable
@Table(name = "`timesheet_log`")
@Subselect("select uuid() as id, tl.* from timesheet_log as tl")
public class TimesheetLog {
    @Id
    private String id;
    private Integer timesheetId;
    private LocalDateTime updateAt;
    private String avatar;
    private String fullNameEmp;

    @Transient
    @Column(name = "time_to_string")
    private String timeToString;

    @Transient
     @Column(name = "date_to_string")
    String dateToString;

    public TimesheetLog() {

    }

    public String getId() {
        return  id;
    }

    public Integer getTimesheetId() {
        return  timesheetId;
    }

    public LocalDateTime getUpdateAt(){
        return updateAt;
    }

    public String getFullNameEmp() {
        return fullNameEmp;
    }

    public String getAvatar(){
        return avatar;
    }

    public String getTimeToString() {
        return updateAt.getHour() + ":" + updateAt.getMinute() + ":" + updateAt.getSecond();
    }

    public  String getDateToString() {
        return updateAt.toLocalDate().toString();
    }


}
