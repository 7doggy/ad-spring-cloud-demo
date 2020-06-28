package com.lichun.ad.sponsor.dao;

import com.lichun.ad.sponsor.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    AdUnit findAdUnitByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAdUnitsByUnitStatus(Integer unitStatus);

}
