package com.managementemployee.admin.furlough.service;

import com.managementemployee.admin.furlough.model.Furlough;


import java.time.LocalDate;
import java.util.List;


public interface FurloughService {
    public Furlough saveFurlough(int empId, Furlough request);

    public List<Furlough> getAllFurlough() ;

    public List<Furlough> getAllByToday();

    public Furlough getById(Integer fId);

    public List<Furlough> getAllByOffFrom(LocalDate offFrom);

    public List<Furlough> getAllByOffTo(LocalDate  offTo);

    public List<Furlough> getAllByDate(LocalDate date);

    public List<Furlough> getAllByEmpId(Integer empId);

    public List<Furlough> getAllEmpByDeniedFurlough();

    public List<Furlough> getAllByEmpAcceptedFurlough();

    public List<Furlough> getAllByEmpAcceptedFurloughToday();

    public Furlough acceptFurlough(Furlough furlough);

    public Furlough denyFurlough(Furlough furlough);

    public List<Furlough> acceptListFurlough(List<Furlough> furloughList);

    public List<Furlough> denyListFurlough(List<Furlough> furloughList);

    public Furlough updateFurlough(Furlough furlough);
}
