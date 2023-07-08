package com.managementemployee.admin.stats.service;


import com.managementemployee.admin.employee.repository.EmployeeRepository;
import com.managementemployee.admin.furlough.model.Furlough;
import com.managementemployee.admin.furlough.repository.FurloughRepository;
import com.managementemployee.admin.stats.model.Statistic;
import com.managementemployee.admin.stats.repository.StatisticRepository;
import com.managementemployee.admin.timesheet.model.Timesheet;
import com.managementemployee.admin.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements  StatisticService{
    @Autowired
    private TimesheetRepository timesheetRepository;


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
     private  FurloughRepository furloughRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    private static final String DATE_PATTERN = "M/yyyy";

    @Override
    public List<Statistic> getAllByMonth(Integer month)  {
       List<Statistic> result = new ArrayList<>();

       try{
           for(var item : employeeRepository.findAll()){
               Statistic statistic = getAllByEmpIdAndMoth(item.getEmpIdInt(), month);
               if (statistic == null) {
                  continue;
               }
               result.add(statistic);

           }

       }
       catch (Exception exception){
           System.out.println("Exception");
       }

       return statisticRepository.saveAll(result);

    }

    @Override
    public Statistic getAllByEmpIdAndMoth(Integer empId, Integer month) {
        Statistic result = new Statistic();
        List<Timesheet> timesheetList = timesheetRepository.findAllByEmpIdAndMoth(empId, month);
        List<Furlough> furloughList = furloughRepository.findAllByEmpIdAndMonth(empId, month);

        if(timesheetList.size() > 0 ){
            result.setEmpId(empId);
            result.setTotalDaysWork(timesheetList.size());
            result.setTotalStanderWork(27);
            var totalDaysOff = 0;
            for(var item: furloughList){
                if(item.getOffTo().getMonth().getValue() == month && item.getOffFrom().getMonth().getValue() == month){
                    totalDaysOff += item.getTotalDaysOff();
                }
                else if(item.getOffFrom().getMonthValue() != month && item.getOffTo().getMonthValue() == month){
                    DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
                    YearMonth yearMonth = YearMonth.parse(month+ "/2023", pattern);
                    System.out.println(yearMonth);
                    LocalDate date = yearMonth.atEndOfMonth();


                    totalDaysOff += Period.between(date, item.getOffTo()).getDays() + 1;



                }
                else if(item.getOffFrom().getMonthValue() == month && item.getOffTo().getMonthValue() != month){
                    DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
                    YearMonth yearMonth = YearMonth.parse(month+ "/2023", pattern);
                    System.out.println(yearMonth);
                    LocalDate date = yearMonth.atEndOfMonth();

                    totalDaysOff += Period.between(item.getOffFrom(), date).getDays() + 1;


                }
            }

            result.setTotalDaysOffHaveFurlough(totalDaysOff);
            result.setTotalDaysWorkPTO(result.getTotalDaysWork() + result.getTotalDaysOffHaveFurlough());


            return statisticRepository.save(result);
        }
        else {
            throw new IllegalArgumentException("Invalid timesheet!");

        }

    }
}
