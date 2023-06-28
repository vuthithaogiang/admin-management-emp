package com.managementemployee.admin.furlough.repository;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.furlough.model.Furlough;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface FurloughRepository extends JpaRepository<Furlough, Integer> {

    List<Furlough> findByOffFrom(LocalDate offFrom);

    List<Furlough> findByOffTo(LocalDate offTo);

    List<Furlough> findByEmployee(Employee employee);

    List<Furlough> findByStatus(Integer status);


    @Query( value = "SELECT * FROM furlough as f WHERE f.emp_id = ?1 and (MONTH(f.off_from) = ?2 or MONTH(F.off_to) = ?2) AND f.status = 1", nativeQuery = true)
    List<Furlough> findAllByEmpIdAndMonth(Integer empId, Integer month);


}
