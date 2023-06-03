package com.managementemployee.admin.furlough.repository;

import com.managementemployee.admin.furlough.model.Furlough;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurloughRepository extends JpaRepository<Furlough, Integer> {
}
