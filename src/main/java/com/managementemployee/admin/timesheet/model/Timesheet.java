package com.managementemployee.admin.timesheet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.managementemployee.admin.common.entity.BaseEntity;
import com.managementemployee.admin.employee.model.Employee;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity (name = "Timesheet")
@Table(name = "timesheet")
public class Timesheet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_id")
    private int  timesheetId;

    @Column(name = "date_in")
    private LocalDate dateIn;

    @Column(name = "time_in")
    private LocalDateTime timeIn;

    @Column(name = "time_out")
    private LocalDateTime timeOut;

    @Column(columnDefinition = "TINYINT(1)")
    private Integer status;

    @Transient
    private Long totalHourWork;


    @ManyToOne
    @JoinColumn(name = "emp_id", columnDefinition = "NOT NULL")
    private Employee employee;



    public Timesheet() {

    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return this.employee;
    }

    @PrePersist
    private void setDefaultDateIn() {
        if(dateIn == null){
            dateIn = LocalDate.now();
        }
    }

    public String getTimesheetId() {
        return "TIMES" + this.timesheetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    public Long getToTalHourWork(){

        if(timeIn != null && timeOut!= null){
            Duration duration = Duration.between(timeIn, timeOut);
            return duration.toHours();
        }
        return null;
    }


    public void setTotalHourWork(Long hours){
        this.totalHourWork = hours;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }
}
