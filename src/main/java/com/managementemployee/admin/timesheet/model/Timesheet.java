package com.managementemployee.admin.timesheet.model;


import com.managementemployee.admin.common.entity.BaseEntity;
import com.managementemployee.admin.employee.model.Employee;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Entity (name = "Timesheet")
@Table(name = "timesheet")
@Validated
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


    @Min(1)
    @Max(5)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sequence;


    @ManyToOne
    @JoinColumn(name = "emp_id")
    @NotNull
    private Employee employee;



    private Long minusLate;



    public Timesheet() {
         this.sequence = 1;

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

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence){this.sequence = sequence;}

    public Long getMinusLate() {
        return this.minusLate;
    }

    public void setMinusLate(Long minusLate){
        this.minusLate = minusLate;
    }
}
