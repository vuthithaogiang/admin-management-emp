package com.managementemployee.admin.timesheet.service;

import com.managementemployee.admin.common.exception.InvalidTimeInException;
import com.managementemployee.admin.common.exception.InvalidTimeOutException;
import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.repository.EmployeeRepository;
import com.managementemployee.admin.furlough.repository.FurloughRepository;
import com.managementemployee.admin.timesheet.model.Timesheet;
import com.managementemployee.admin.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class TimesheetServiceImpl implements TimesheetService {
    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FurloughRepository furloughRepository;

    @Override
    public List<Timesheet> getAllTimesheet() {
        return timesheetRepository.findAll();
    }

    @Override
    public Timesheet saveToTrash(Integer timesheetId) {
       Timesheet timesheet = timesheetRepository.findById(timesheetId).orElse(null);

       if(timesheet == null){
           return null;
       }
       else{
           timesheet.setTrash(1);
           return timesheetRepository.save(timesheet);
       }
    }

    @Override
    public Timesheet toRestoreFromTrash(Integer timesheetId) {
       Timesheet timesheet = timesheetRepository.findById(timesheetId).orElse(null);

       if(timesheet == null){
           return null;
       }
       else{
           timesheet.setTrash(0);
           return timesheetRepository.save(timesheet);
       }

    }


    @Override
    public List<Timesheet> getAllInTrash(){
        return timesheetRepository.findAllByTrash(1);
    }

    @Override
    public List<Timesheet> getAllByEmpIdAndMonth(Integer empId, Integer month) {
        return timesheetRepository.findAllByEmpIdAndMoth(empId, month);
    }

    @Override
    public Timesheet editTimesheet(Timesheet timesheet){
        Timesheet timesheetExisting = timesheetRepository.findById(timesheet.getTimesheetId()).orElse(null);

        if(timesheetExisting == null) {
            return null;
        }
        else{
            if(timesheetExisting.getTrash() == 1){
                return null;
            }
            else{
                try{
                    timesheetExisting.setTimeIn(timesheet.getTimeIn());
                    timesheetExisting.setTimeOut(timesheet.getTimeOut());
                    return timesheetRepository.save(timesheetExisting);
                }
                catch (Exception e) {
                    return null;
                }

            }
        }
    }

    @Override
    public String deleteTimesheet(Integer timesheetId) {
        return null;
    }


    @Override
    public Timesheet saveTimesheet(int empId) throws InvalidTimeOutException, InvalidTimeInException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
        LocalTime timeInEndValid = LocalTime.parse("8:31:00", dtf);
        var presentTime = LocalTime.now();
        int sizeOfEmpPresent = getTimesheetByEmployeeId(empId).size();
        var EmpPresentToday = getTimesheetByEmployeeIdAndDateNow(empId);

        if(sizeOfEmpPresent == 0 | EmpPresentToday == null){
            try{
                Employee employee = employeeRepository.findById(empId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID:" + empId));

                Timesheet timesheet = new Timesheet();
                timesheet.setEmployee(employee);
                timesheet.setTimeIn(LocalDateTime.now());
                timesheet.setTrash(0);

                if(presentTime.isBefore(timeInEndValid)){
                    timesheet.setMinusLate(0L);
                }
                else{
                    timesheet.setMinusLate(Duration.between(timeInEndValid, presentTime).toMinutes());
                }
                timesheet.setStatus(1);
                return timesheetRepository.save(timesheet);
            }
            catch (InvalidTimeInException e){
             String message = "Invalid time in!";
             throw new  InvalidTimeInException(message, presentTime);
            }

        }
        else{
            Timesheet timesheet = getTimesheetByEmployeeIdAndDateNow(empId);
            Duration duration = Duration.between( timesheet.getTimeIn(), LocalDateTime.now());

            System.out.println(duration.toMinutes());

            if(timesheet.getTimeOut() == null && ( duration.toMinutes() >= 60*3 )){
                try{
                    timesheet.setTimeOut(LocalDateTime.now());
                    return timesheetRepository.save(timesheet);
                }
                catch (InvalidTimeOutException e){
                    String message = "Invalid time out!";
                    throw new InvalidTimeOutException(message, presentTime);

                }
            }
            else {
                if(timesheet.getSequence() < 5) {
                    timesheet.setSequence(timesheet.getSequence() + 1);

                }
                return timesheetRepository.save(timesheet);
            }

        }
    }



    @Override
    public List<Timesheet> getTimesheetByEmployeeId(int empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));

         List<Timesheet> list = timesheetRepository.findByEmployee(employee);
         List<Timesheet> result = new ArrayList<>();

         for(var item : list){
             if(item.getTrash() == 0){
                 result.add(item);
             }
         }

         return result;

    }

    @Override
    public Timesheet getTimesheetByEmployeeIdAndDateNow(int empId) {
        List<Timesheet> timesheetList  = getTimesheetByEmployeeId(empId);

        if(timesheetList.size() > 0  ){
            for(var item: timesheetList){
                if(item.getDateIn().isEqual(LocalDate.now()) && item.getTrash() == 0){
                    return item;
                }
            }
        }
         return null;
    }

    @Override
    public List<Timesheet> getTimesheetByStatus(int status){
        List<Timesheet> result = new ArrayList<>();

        for(var item : timesheetRepository.findAll()){
            if(item.getStatus() == status && item.getTrash() == 0){
                result.add(item);
            }
        }

        return result;
    }

    @Override
    public List<Timesheet> saveDefaultEmpAcceptedFurloughAbsent() {
        List<Timesheet> timesheetList = new ArrayList<>();
        var date = LocalDate.now();

        List<Employee> employeeList = new ArrayList<>();

        for(var item : furloughRepository.findByStatus(1)) {
            if( date.isBefore(item.getOffTo()) && date.isAfter(item.getOffFrom() )
                    | date.isEqual(item.getOffTo()) | date.isEqual(item.getOffFrom()))

            {
                employeeList.add(item.getEmployee());
            }
        }

        for(int i = 0 ; i < employeeList.size(); i ++){
            Timesheet timesheet = new Timesheet();
            timesheet.setEmployee(employeeList.get(i));
            timesheet.setDateIn(date);
            timesheet.setStatus(0);

            timesheetList.add(timesheet);
        }
         return timesheetRepository.saveAll(timesheetList);

    }

    public boolean timeInValid(LocalTime timeInput){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
        LocalTime timeInBeginValid = LocalTime.parse("7:00:00", dtf );
        LocalTime timeInEndValid = LocalTime.parse("8:30:00", dtf);


        if(timeInput.isAfter(timeInBeginValid) && timeInput.isBefore(timeInEndValid)){
            return true;
        }
        return false;

    }

    public boolean timeOutValid(LocalTime timeInput){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
        LocalTime timeOutBeginValid = LocalTime.parse("18:00:00", dtf);
        LocalTime timeOutEndValid = LocalTime.parse("19:00:00", dtf);

        if(timeInput.isAfter(timeOutBeginValid) && timeInput.isBefore(timeOutEndValid)){
            return true;
        }
        return  false;

    }
}
