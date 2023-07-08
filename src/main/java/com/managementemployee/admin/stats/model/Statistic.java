package com.managementemployee.admin.stats.model;

import com.managementemployee.admin.common.entity.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "`statistic`")
public class Statistic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    private Integer empId;

    @Transient
    private Integer totalDaysWork;

    @Transient
    private Integer totalDaysOffHaveFurlough;

    @Transient
    private Integer totalStanderWork;

    @Transient
    private Integer totalDaysWorkPTO;

    public Statistic() {

    }

    public Integer getId(){
        return id;
    }

    public Integer getEmpId(){
        return empId;
    }

    public void setEmpId(Integer empId){
        this.empId = empId;
    }

    public Integer getTotalDaysWork(){
        return totalDaysWork;
    }

    public void setTotalDaysWork(Integer totalDaysWork){
        this.totalDaysWork = totalDaysWork;
    }

    public Integer getTotalDaysOffHaveFurlough(){
        return totalDaysOffHaveFurlough;
    }

    public void setTotalDaysOffHaveFurlough(Integer totalDaysOffHaveFurlough){
        this.totalDaysOffHaveFurlough = totalDaysOffHaveFurlough;
    }

    public Integer getTotalStanderWork() {
        return totalStanderWork;
    }

    public void setTotalStanderWork(Integer standerWork){
        this.totalStanderWork = standerWork;
    }

    public Integer getTotalDaysWorkPTO(){
        return this.totalDaysWorkPTO;
    }

    public void setTotalDaysWorkPTO(Integer totalDaysWorkPTO){
        this.totalDaysWorkPTO = totalDaysWorkPTO;
    }
}
