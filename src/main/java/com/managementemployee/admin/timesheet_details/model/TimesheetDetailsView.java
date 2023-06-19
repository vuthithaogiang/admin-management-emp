package com.managementemployee.admin.timesheet_details.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Immutable
@Table(name = "`timesheet_details`")
@Subselect("select uuid() as id, td.* from timesheet_details as td ORDER by time_in ASC")
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

    @Column(name = "total_work")
    @Transient
    private Long totalWork;

    private Integer empId;

    private String avatar;

    private String firstName;

    @Column(name = "full_name_emp")
    private String fullNameEmp;

    private String position;


    @Column(name = "time_in_string")
    @Transient
    private String timeInString;

    @Column(name="time_in_out")
    @Transient
    private  String timeOutString;

    @Column(name = "day_off_week")
    @Transient
    private String dayOfWeek;


    @Transient
    private String monthInString;

    @Transient
    private Integer dayOfMonth;


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

    public Long getTotalWork() {
        if(timeIn != null && timeOut!= null){
            Duration duration = Duration.between(timeIn, timeOut);
            return duration.toHours();
        }
        return null;
    }

    public String getTimeInString() {
        if(timeIn != null) {
            return timeIn.getHour() + ":" + timeIn.getMinute() + ":" + timeIn.getSecond();
        }
        return null;
    }

    public String getTimeOutString() {
        if(timeOut != null){
            return timeOut.getHour() + ":" + timeOut.getMinute() + ":" + timeOut.getSecond();
        }
        return null;
    }

    public String getDayOfWeek() {
        return timeIn.getDayOfWeek().toString();
    }

     public String getFirstName() { return firstName;}

    public String getMonthInString() {
        String months[] = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December"};

        int monthIndex = dateIn.getMonthValue();

        return months[monthIndex -1];
    }

    public Integer getDayOfMonth() {
        return dateIn.getDayOfMonth();
    }
}
