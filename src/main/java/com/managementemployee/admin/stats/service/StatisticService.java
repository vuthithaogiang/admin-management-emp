package com.managementemployee.admin.stats.service;

import com.managementemployee.admin.stats.model.Statistic;

import java.util.List;

public interface StatisticService {

    public Statistic getAllByEmpIdAndMoth(Integer empId, Integer month);

    public List<Statistic> getAllByMonth(Integer month);
}
