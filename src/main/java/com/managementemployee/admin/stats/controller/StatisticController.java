package com.managementemployee.admin.stats.controller;

import com.managementemployee.admin.stats.model.Statistic;
import com.managementemployee.admin.stats.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = "*")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/empId={empId}&month={month}")
    public Statistic getAllByEmpIdAndMonth(@PathVariable Integer empId, @PathVariable Integer month){
        return statisticService.getAllByEmpIdAndMoth(empId, month);
    }

    @GetMapping("/month={month}")
    public List<Statistic> getAllByMonth(@PathVariable Integer month){
        return statisticService.getAllByMonth(month);
    }
}
