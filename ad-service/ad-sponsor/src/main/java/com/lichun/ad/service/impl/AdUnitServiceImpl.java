package com.lichun.ad.service.impl;

import com.lichun.ad.constant.Constants;
import com.lichun.ad.dao.AdPlanRepository;
import com.lichun.ad.dao.AdUnitRepository;
import com.lichun.ad.entity.AdPlan;
import com.lichun.ad.entity.AdUnit;
import com.lichun.ad.exception.AdException;
import com.lichun.ad.service.IAdUnitService;
import com.lichun.ad.vo.AdUnitRequest;
import com.lichun.ad.vo.AdUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdUnitServiceImpl implements IAdUnitService {

    private AdPlanRepository planRepository;
    private AdUnitRepository unitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository, AdUnitRepository unitRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request)  throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldAdUnit = unitRepository.findAdUnitByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (oldAdUnit != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        AdUnit newAdUnit = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));
        return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
    }

}
