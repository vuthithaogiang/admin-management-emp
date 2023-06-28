package com.managementemployee.admin.furlough.controller;


import com.managementemployee.admin.furlough.model.Furlough;
import com.managementemployee.admin.furlough.service.FurloughService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/furlough")
@CrossOrigin(origins = "*")
public class FurloughController {

    @Autowired
    private FurloughService furloughService;

    //http://localhost:8080/furlough?empId=5
    @PostMapping
    @ResponseBody
    public Furlough addFurlough(@RequestParam int empId,
                                @RequestBody Furlough request){
        return   furloughService.saveFurlough(empId, request);

    }

    @GetMapping("/getAll")
    public List<Furlough> getAll() {
        return furloughService.getAllFurlough();
    }

    @GetMapping("/getById/{fId}")
    public Furlough getById( @PathVariable Integer fId){
        return furloughService.getById(fId);
    }

    @GetMapping("/getAllByToday")
    public List<Furlough> getAllByToday() {
        return furloughService.getAllByToday();
    }

    @GetMapping("/getAllByOffFrom/{offFrom}")
    public List<Furlough> getAllByOffFrom(@PathVariable LocalDate offFrom){
        return furloughService.getAllByOffFrom(offFrom);
    }

    @GetMapping("/getAllByOffTo/{offTo}")
    public List<Furlough> getAllByOffTo(@PathVariable LocalDate offTo){
        return furloughService.getAllByOffTo(offTo);
    }

    @GetMapping("/getAllByDate/{date}")
    public List<Furlough> getAllByDate(@PathVariable LocalDate date){
        return furloughService.getAllByDate(date);
    }

    @GetMapping("/getAllByEmpId/{empId}")
    public List<Furlough> getAllByEmpId(@PathVariable Integer empId){
        return furloughService.getAllByEmpId(empId);
    }

    @GetMapping("/getAllAccepted")
    public List<Furlough> getAllByEmpAcceptedFurlough(){
        return  furloughService.getAllByEmpAcceptedFurlough();
    }

    @GetMapping("/getAllDenied")
    public List<Furlough> getAllByDeniedFurlough( ) {
        return furloughService.getAllEmpByDeniedFurlough();
    }

    @GetMapping("/getAllByEmpIdAndMonth/empId={empId}&month={month}")
    public List<Furlough> getAllByEmpIdAndMonth(@PathVariable Integer empId, @PathVariable Integer month ){
        return furloughService.getAllByEmpIdAndMonth(empId, month);
    }

    @GetMapping("/getAllByEmpAcceptedToday")
    public List<Furlough> getAllByEmpAcceptedToday() {
        return furloughService.getAllByEmpAcceptedFurloughToday();
    }

    @PatchMapping("/accept")
    public Furlough acceptFurlough(@RequestBody Furlough furlough){
        return furloughService.acceptFurlough(furlough);
    }

    @PatchMapping("/deny")
    public Furlough denyFurlough(@RequestBody Furlough furlough){
        return furloughService.denyFurlough(furlough);
    }


    @PatchMapping("/acceptList")
   public List<Furlough> acceptListFurlough(@RequestBody List<Furlough> furloughList){
        return furloughService.acceptListFurlough(furloughList);
   }

   @PatchMapping("/denyList")
   public List<Furlough> denyListFurlough(@RequestBody List<Furlough> furloughList){
        return furloughService.denyListFurlough(furloughList);
   }


   @PatchMapping("/update")
   public Furlough updateFurlough(@RequestBody Furlough furlough){
        return furloughService.updateFurlough(furlough);
   }

   @DeleteMapping("/delete/{furloughId}")
   public String deleteFurlough(@PathVariable Integer furloughId){
        return furloughService.deleteFurlough(furloughId);
   }

}
