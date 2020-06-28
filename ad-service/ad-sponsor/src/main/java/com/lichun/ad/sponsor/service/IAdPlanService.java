package com.lichun.ad.sponsor.service;

import com.lichun.ad.sponsor.entity.AdPlan;
import com.lichun.ad.sponsor.exception.AdException;
import com.lichun.ad.sponsor.vo.AdPlanGetRequest;
import com.lichun.ad.sponsor.vo.AdPlanRequest;
import com.lichun.ad.sponsor.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
