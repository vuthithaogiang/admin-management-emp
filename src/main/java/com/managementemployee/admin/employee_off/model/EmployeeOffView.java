package com.managementemployee.admin.employee_off.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "`employee_off`")
@Subselect("select uuid() as id, eo.* from employee_off as eo")
public class EmployeeOffView {

    @Id
    private String id;

    private Integer empId;
    private String avatar;
    private String fullNameEmp;
    private LocalDateTime createAt;

    @Column(name = "off_from")
    private LocalDate offFrom;

    @Column(name = "off_to")
    private  LocalDate offTo;

    private String note;

    @Column(columnDefinition = "TINYINT(1)")
    private Integer status;

    @Column(name = "total_days_off")
    private Integer totalDaysOff;

    public EmployeeOffView() {

    }

    public String getId() { return id;}

    public Integer getEmpId() {return  empId;}

    public String getAvatar() { return  avatar;}

    public String getFullNameEmp() { return fullNameEmp;}

    public LocalDateTime getCreateAt() { return createAt;}

    public LocalDate getOffFrom() { return  offFrom;}

    public LocalDate getOffTo() { return offTo;}

    public String getNote() { return note;}

    public Integer getStatus(){ return  status;}

    public Integer getTotalDaysOff() { return  totalDaysOff;}
}
