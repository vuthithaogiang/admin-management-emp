package com.managementemployee.admin.timesheet.service;

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

                timesheetExisting.setTimeIn(timesheet.getTimeIn());
                timesheetExisting.setTimeOut(timesheet.getTimeOut());


                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
                LocalTime time = LocalTime.parse("8:30:00", dtf);

                if(Duration.between(time , timesheet.getTimeIn().toLocalTime() ).toMinutes() <= 0){
                    timesheetExisting.setMinusLate(0L);
                }
                else{
                    timesheetExisting.setMinusLate(Duration.between(time ,
                            timesheet.getTimeIn().toLocalTime() ).toMinutes());
                }


                return timesheetRepository.save(timesheetExisting);
            }
        }
    }

    @Override
    public String deleteTimesheet(Integer timesheetId) {
        return null;
    }

    @Override
    public Timesheet saveTimesheet( int empId) {

        var presentTime = LocalTime.now();

       int sizeOfEmpPresent = getTimesheetByEmployeeId(empId).size();
       var EmpPresentToday = getTimesheetByEmployeeIdAndDateNow(empId);

        // chưa có ất kì nhân viên nào
        // hoặc hôm nay chưa có nhân viên nào check in
        // NEW RECORD
        if(sizeOfEmpPresent == 0 || EmpPresentToday == null) {
            Employee employee = employeeRepository.findById(empId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));


            Timesheet timesheet = new Timesheet();
            timesheet.setEmployee(employee);
            //timesheet.setDateIn(LocalDate.now());
            timesheet.setTimeIn(LocalDateTime.now());
            timesheet.setTrash(0);

            if(timeInValid(LocalTime.now())){
                timesheet.setMinusLate(0L);
            }
            else{
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
                LocalTime time = LocalTime.parse("8:30:00", dtf);
                timesheet.setMinusLate(Duration.between(time, LocalTime.now()).toMinutes());
                System.out.println(Duration.between(time, LocalTime.now()).toMinutes());

            }

            timesheet.setStatus(1);
            return timesheetRepository.save(timesheet);

        }
        // CHECK TIME OUT VALID ?
        else{
            Timesheet timesheet = getTimesheetByEmployeeIdAndDateNow(empId);
            Duration duration = Duration.between( timesheet.getTimeIn(), LocalDateTime.now());

            System.out.println(duration.toMinutes());

            // gia su check out success neu da lm dc it nhat 1 shift: 3hours
            // hoac da den gio ve
            if(timesheet.getTimeOut() == null &&
                    ( duration.toMinutes() > 60*3 || timeOutValid(LocalTime.now()) )
            ){
                timesheet.setTimeOut(LocalDateTime.now());
            }
            else {
                if(timesheet.getSequence() < 5) {
                    timesheet.setSequence(timesheet.getSequence() + 1);
                }
            }


            return timesheetRepository.save(timesheet);
        }


    }
    @Override
    public List<Timesheet> getAllTimesheet() {

        return timesheetRepository.findAllByTrash(0);
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
