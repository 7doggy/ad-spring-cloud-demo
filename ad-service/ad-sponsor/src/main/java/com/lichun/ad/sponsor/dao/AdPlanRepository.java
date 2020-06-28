package com.lichun.ad.sponsor.dao;

import com.lichun.ad.sponsor.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {
    AdPlan findAdPlanByIdAndUserId(Long id, Long userId);

    List<AdPlan> findAdPlansByIdInAndUserId(List<Long> ids, Long userId);

    AdPlan findAdPlanByUserIdAndPlanName(Long userId, String planName);

    List<AdPlan> findAdPlansByPlanStatus(Integer status);
}
