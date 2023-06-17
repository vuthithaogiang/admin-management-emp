package com.managementemployee.admin.common.entity;

import java.time.LocalDate;

public class DateDefinition {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public  DateDefinition() {

    }

    public DateDefinition(LocalDate dateFrom, LocalDate dateTo){
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
    }
    public LocalDate getDateTo() {return dateTo;}

    public LocalDate getDateFrom() {return  dateFrom;}

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo){
        this.dateTo = dateTo;
    }
}
