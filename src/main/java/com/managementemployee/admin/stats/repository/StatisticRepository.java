package com.managementemployee.admin.stats.repository;

import com.managementemployee.admin.stats.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

}
