package com.managementemployee.admin.furlough.model;

import com.managementemployee.admin.common.entity.BaseEntity;
import com.managementemployee.admin.employee.model.Employee;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Furlough")
@Table(name  = "furlough")
public class Furlough extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int furloughId;


    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee createByOwner;

    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String note;

    @Column(name = " off_from", nullable = false)
    private LocalDateTime offFrom;

    @Column(name = "off_to"  , nullable = false)
    private LocalDateTime offTo;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer status;

    public Furlough() {

    }

    public String getFurloughId() {
        return "FUR" + this.furloughId;
    }

    public Employee getCreateByOwner(){
        return this.createByOwner;
    }

    public void setCreateByOwner(Employee employee){
        this.createByOwner = employee;
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

    public LocalDateTime getOffFrom() {
        return offFrom;
    }

    public void setOffFrom(LocalDateTime offFrom) {
        this.offFrom = offFrom;
    }

    public LocalDateTime getOffTo() {
        return offTo;
    }

    public void setOffTo(LocalDateTime offTo) {
        this.offTo = offTo;
    }

    @PrePersist
    private void setOffFromDefault() {
        if(offFrom == null){
            this.offFrom = LocalDateTime.now();
        }
    }
}
