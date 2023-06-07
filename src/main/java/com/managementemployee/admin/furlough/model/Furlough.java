package com.managementemployee.admin.furlough.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.managementemployee.admin.common.entity.BaseEntity;
import com.managementemployee.admin.employee.model.Employee;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import  java.time.format.DateTimeFormatter;

import java.util.Locale;

@Entity(name = "Furlough")
@Table(name  = "furlough")
public class Furlough extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "furlough_id")
    private int furloughId;



    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String note;

    @Column(name = " off_from", nullable = false)
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd" , shape = JsonFormat.Shape.STRING)
    private LocalDate offFrom;

    @Column(name = "off_to"  , nullable = false)
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd"  , shape = JsonFormat.Shape.STRING)
    private LocalDate offTo;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer status;


    @Column(name = "total_days_off")
    private Integer totalDaysOff;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    @NotNull
    private Employee employee;


    public Furlough() {

    }


    public String getFurloughId() {
        return "FUR" + this.furloughId;
    }

    public Integer getFurLoughInteger() {return this.furloughId;}

    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public String note() {
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public Integer getStatus(){
        return  this.status;
    }

    public void setStatus(Integer status){
        if(status == 0 | status == 1) {
            this.status = status;
        }

    }

    public LocalDate getOffFrom() {
        return offFrom;
    }

    public void setOffFrom(String offFrom) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.of("vi", "VN"));
        LocalDate date = LocalDate.parse(offFrom, formatter);
        System.out.println(date);
        this.offFrom = date;

    }

    public LocalDate getOffTo() {
        return offTo;
    }


    public void setOffTo(String offTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.of("vi", "VN"));
        LocalDate date = LocalDate.parse(offTo, formatter);
        System.out.println(date);
        this.offTo= date;

    }


    public String getNote() {
        return note;
    }

    public Integer getTotalDaysOff() {
        return this.totalDaysOff;
    }

    public void setTotalDaysOff(Integer totalDaysOff){
        this.totalDaysOff = totalDaysOff;
    }

}
