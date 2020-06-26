package com.lichun.ad.service;

import com.lichun.ad.entity.AdPlan;
import com.lichun.ad.exception.AdException;
import com.lichun.ad.vo.AdPlanGetRequest;
import com.lichun.ad.vo.AdPlanRequest;
import com.lichun.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
