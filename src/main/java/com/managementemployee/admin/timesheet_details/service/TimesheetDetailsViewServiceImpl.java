package com.managementemployee.admin.timesheet_details.service;


import com.managementemployee.admin.common.entity.DateDefinition;
import com.managementemployee.admin.timesheet_details.model.TimesheetDetailsView;
import com.managementemployee.admin.timesheet_details.repository.TimesheetDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Service
public class TimesheetDetailsViewServiceImpl  implements TimesheetDetailsViewService{

    @Autowired
   private TimesheetDetailsViewRepository timesheetDetailsViewRepository;

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetails() {
        return timesheetDetailsViewRepository.findAll();
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsByEmpId(Integer empId) {

         List<TimesheetDetailsView> list = timesheetDetailsViewRepository.findAll();

         List<TimesheetDetailsView> result = new ArrayList<>();

         for(var item : list){
             if(item.getEmpId() == empId){
                 result.add(item);
             }
         }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdInWeek(Integer empId) {
        LocalDate today = LocalDate.now();

        LocalDate monday = today.with(previousOrSame(MONDAY));
        LocalDate sunday = today.with(nextOrSame(SUNDAY));
        System.out.println("Today: " + today);
        System.out.println("Monday of the Week: " + monday);
        System.out.println("Sunday of the Week: " + sunday);

        List<TimesheetDetailsView> list = timesheetDetailsViewRepository.findAll();

        List<TimesheetDetailsView> result = new ArrayList<>();

        for(var item : list){
            if( (item.getDateIn().isAfter(monday) && item.getDateIn().isBefore(sunday)
                    || item.getDateIn().isEqual(monday) || item.getDateIn().isEqual(sunday))
                    && item.getEmpId() == empId){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdToday(Integer empId) {
        List<TimesheetDetailsView> list = timesheetDetailsViewRepository.findAll();

        List<TimesheetDetailsView> result = new ArrayList<>();

        LocalDate today = LocalDate.now();


        for (var item : list){
            if(item.getDateIn().isEqual(today) && item.getEmpId() == empId){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsInToday() {
      LocalDate today = LocalDate.now();

      List <TimesheetDetailsView> result = new ArrayList<>();

      for(var item : timesheetDetailsViewRepository.findAll()){
          if(item.getDateIn().isEqual(today)){
              result.add(item);
          }
      }
      return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailInThisWeek() {
        LocalDate today = LocalDate.now();

        LocalDate monday = today.with(previousOrSame(MONDAY));
        LocalDate sunday = today.with(nextOrSame(SUNDAY));


        List<TimesheetDetailsView> list = timesheetDetailsViewRepository.findAll();

        List<TimesheetDetailsView> result = new ArrayList<>();

        for(var item : list){
            if( (item.getDateIn().isAfter(monday) && item.getDateIn().isBefore(sunday)
                    || item.getDateIn().isEqual(monday) || item.getDateIn().isEqual(sunday))
                    ){
                result.add(item);
            }
        }

        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllByLastWeek() {
        LocalDate today = LocalDate.now();

        LocalDate monday = today.with(previousOrSame(MONDAY));
        LocalDate sunday = today.with(nextOrSame(SUNDAY));

        LocalDate lastWeekMonday = monday.minusDays(7);
        LocalDate lasWeekSunday = sunday.minusDays(7);

        System.out.println("Today: " + today);
        System.out.println("Monday of the Week: " + monday);
        System.out.println("Sunday of the Week: " + sunday);
        System.out.println("Last Monday" + lastWeekMonday);
        System.out.println(("Last sunday: " + lasWeekSunday));

        List<TimesheetDetailsView> list = timesheetDetailsViewRepository.findAll();

        List<TimesheetDetailsView> result = new ArrayList<>();

        for(var item : list){
            if( (item.getDateIn().isAfter(lastWeekMonday) && item.getDateIn().isBefore(lasWeekSunday)
                    || item.getDateIn().isEqual(lastWeekMonday) || item.getDateIn().isEqual(lasWeekSunday))
            ){
                result.add(item);
            }
        }

        return result;

    }


    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdLastWeek(Integer empId){
        List<TimesheetDetailsView> result = new ArrayList<>();

        List<TimesheetDetailsView> timesheetInLastWeek = findAllByLastWeek();

        for(var item : timesheetInLastWeek){
            if(item.getEmpId() == empId){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsInThisMoth() {
        List<TimesheetDetailsView> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for(var item : timesheetDetailsViewRepository.findAll()){
            if(item.getDateIn().getMonth() == today.getMonth()){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimeSheetDetailsViewFromDateToOtherDate
            (DateDefinition date) {
        List<TimesheetDetailsView> result = new ArrayList<>();
        for(var item : timesheetDetailsViewRepository.findAll()) {
            if(item.getDateIn().isAfter(date.getDateFrom()) && item.getDateIn().isBefore(date.getDateTo())
                    || item.getDateIn().isEqual(date.getDateFrom()) || item.getDateIn().isEqual(date.getDateTo())){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<TimesheetDetailsView> findAllTimesheetDetailsViewByEmpIdFromDateToOtherDate(DateDefinition date ,
                                                                                            Integer empId) {
        List<TimesheetDetailsView> result = new ArrayList<>();
        for(var item : timesheetDetailsViewRepository.findAll()) {
            if((item.getDateIn().isAfter(date.getDateFrom()) && item.getDateIn().isBefore(date.getDateTo())
                    || item.getDateIn().isEqual(date.getDateFrom()) || item.getDateIn().isEqual(date.getDateTo()))
            && item.getEmpId() == empId){
                result.add(item);
            }
        }
        return result;
    }
}
