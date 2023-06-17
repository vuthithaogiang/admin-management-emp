package com.managementemployee.admin.furlough.service;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.employee.repository.EmployeeRepository;
import com.managementemployee.admin.furlough.model.Furlough;
import com.managementemployee.admin.furlough.repository.FurloughRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Service
public class FurloughServiceImpl implements FurloughService {
    @Autowired
    private FurloughRepository furloughRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Furlough saveFurlough(int empId, Furlough request) {
            Employee employee = employeeRepository.findById(empId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));

            Furlough furlough = new Furlough();

            System.out.println("DATE:" + request.getOffTo());
            furlough.setEmployee(employee);
            furlough.setNote(request.getNote());
            furlough.setOffFrom(request.getOffFrom().toString());
            furlough.setOffTo(request.getOffTo().toString());
            furlough.setStatus(0);
            var days = Period.between(request.getOffFrom(), request.getOffTo());

           System.out.println( "Days: " + days.getDays());

           furlough.setTotalDaysOff(days.getDays() +1);
            return furloughRepository.save(furlough);

    }

    @Override
    public List<Furlough> getAllFurlough() {
        return furloughRepository.findAll();
    }

    @Override
    public Furlough getById(Integer fId){
        return furloughRepository.findById(fId).orElse(null);
    }

    @Override
    public List<Furlough> getAllByToday() {
        var d = LocalDate.now();
        List<Furlough> list = getAllFurlough();
        List<Furlough> result = new ArrayList<>();
        for(Furlough item : list){
            if(d.isBefore(item.getOffTo()) && d.isAfter(item.getOffFrom())
                    | d.isEqual(item.getOffTo()) | d.isEqual(item.getOffFrom())){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<Furlough> getAllByOffFrom(LocalDate offFrom){
        return furloughRepository.findByOffFrom(offFrom);
    }

    @Override
    public List<Furlough> getAllByOffTo(LocalDate offTo) {
        return furloughRepository.findByOffTo(offTo);
    }

    @Override
    public List<Furlough> getAllByDate(LocalDate date) {
        List<Furlough> list = getAllFurlough();
        List<Furlough> result = new ArrayList<>();
        for(Furlough item : list){
            if(date.isBefore(item.getOffTo()) && date.isAfter(item.getOffFrom())
                    | date.isEqual(item.getOffTo()) | date.isEqual(item.getOffFrom())){
                result.add(item);
            }
        }
        return result;
    }



    @Override
    public List<Furlough> getAllByEmpId(Integer empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));

        return furloughRepository.findByEmployee(employee);
    }

    @Override
    public List<Furlough> getAllEmpByDeniedFurlough() {
        return furloughRepository.findByStatus(0);
    }

    @Override
    public List<Furlough> getAllByEmpAcceptedFurlough() {
        return furloughRepository.findByStatus(1);
    }

    @Override
    public List<Furlough> getAllByEmpAcceptedFurloughToday() {
        List<Furlough> list = getAllByEmpAcceptedFurlough();

        List<Furlough> result = new ArrayList<>();
        var d = LocalDate.now();

        for(Furlough item : list){
            if(d.isBefore(item.getOffTo()) && d.isAfter(item.getOffFrom())
                    | d.isEqual(item.getOffTo()) | d.isEqual(item.getOffFrom())){
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Furlough acceptFurlough(Furlough furlough) {

        Furlough furloughExisting = furloughRepository.findById(furlough.getFurLoughInteger()).orElse(null);

        furloughExisting.setStatus(1);
        return  furloughRepository.save(furloughExisting);
    }

    @Override
    public Furlough denyFurlough(Furlough furlough) {
        Furlough furloughExisting = furloughRepository.findById(furlough.getFurLoughInteger()).orElse(null);

        furloughExisting.setStatus(0);
        return furloughRepository.save(furloughExisting);
    }

    @Override
    public List<Furlough> acceptListFurlough(List<Furlough> furloughList) {
            List<Furlough> furloughsExisting  = new ArrayList<>();

            for(var item : furloughList){
                if(furloughRepository.findById(item.getFurLoughInteger()) != null){
                    furloughsExisting.add(item);
                }
            }

            for(var item : furloughsExisting){
                item.setStatus(1);
            }

            return furloughRepository.saveAll(furloughsExisting);

    }

    @Override
    public List<Furlough> denyListFurlough(List<Furlough> furloughList) {

        List<Furlough> furloughsExisting = new ArrayList<>();

        for(var item : furloughList){
            if(furloughRepository.findById(item.getFurLoughInteger()) != null){
                furloughsExisting.add(item);
            }
        }

        for(var item : furloughsExisting){
            item.setStatus(0);
        }

        return furloughRepository.saveAll(furloughsExisting);


    }

    @Override
    public Furlough updateFurlough(Furlough furlough) {
        Furlough existingFurlough = furloughRepository.findById(furlough.getFurLoughInteger()).orElse(null);

        existingFurlough.setOffFrom(furlough.getOffFrom().toString());
        existingFurlough.setOffTo(furlough.getOffTo().toString());
        existingFurlough.setNote(furlough.getNote());
        var days = Period.between(furlough.getOffFrom(), furlough.getOffTo());
        existingFurlough.setTotalDaysOff(days.getDays() +1);

        return furloughRepository.save(existingFurlough);

    }

    @Override
    public String deleteFurlough(Integer furloughId) {
        Furlough furloughExisting = furloughRepository.findById(furloughId).orElse(null);

        if(furloughExisting == null){
            return  null;
        }
        else{
            furloughRepository.deleteById(furloughId);
            return "Delete complete";
        }
    }
}
