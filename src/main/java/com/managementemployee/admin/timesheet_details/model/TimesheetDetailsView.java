package com.managementemployee.admin.timesheet_details.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Immutable
@Table(name = "`timesheet_details`")
@Subselect("select uuid() as id, td.* from timesheet_details as td")
public class TimesheetDetailsView implements Serializable {
    @Id
    private String id;

    @Column(name = "timesheet_id")
    private Integer timesheetId;

    @Column(name = "date_in")
    private LocalDate dateIn;

    @Column(name = "time_in")
    private LocalDateTime timeIn;

    @Column(name = "time_out")
    private LocalDateTime timeOut;

    @Column(columnDefinition = "TINYINT(1)")
    private  Integer status;

    @Column(name = "minus_late")
    private Long minusLate;

    private Integer empId;

    private String avatar;

    @Column(name = "full_name_emp")
    private String fullNameEmp;

    private String position;


    public TimesheetDetailsView() {

    }

    public String getId() {
        return  id;
    }

    public Integer getTimesheetId() {
        return timesheetId;
    }



    public LocalDate getDateIn() {
        return dateIn;
    }



    public LocalDateTime getTimeIn() {
        return timeIn;
    }



    public LocalDateTime getTimeOut() {
        return timeOut;
    }



    public Integer getStatus() {
        return status;
    }



    public Long getMinusLate() {
        return minusLate;
    }


    public Integer getEmpId() { return  empId;}
    public String getAvatar() {
        return avatar;
    }



    public String getFullNameEmp() {
        return fullNameEmp;
    }


    public String getPosition() {
        return position;
    }


}
