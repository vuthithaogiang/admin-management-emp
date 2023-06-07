package com.managementemployee.admin.furlough.repository;

import com.managementemployee.admin.employee.model.Employee;
import com.managementemployee.admin.furlough.model.Furlough;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface FurloughRepository extends JpaRepository<Furlough, Integer> {

    List<Furlough> findByOffFrom(LocalDate offFrom);

    List<Furlough> findByOffTo(LocalDate offTo);

    List<Furlough> findByEmployee(Employee employee);

    List<Furlough> findByStatus(Integer status);


}
