package com.idk.api.districtcode.domain.repository;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictCodeRepository extends JpaRepository<DistrictCode, Integer> {
    DistrictCode findById(int districtId);
}
