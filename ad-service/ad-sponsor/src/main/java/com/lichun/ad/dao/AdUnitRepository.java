package com.lichun.ad.dao;

import com.lichun.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    AdUnit findAdUnitByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAdUnitsByUnitStatus(Integer unitStatus);

}
