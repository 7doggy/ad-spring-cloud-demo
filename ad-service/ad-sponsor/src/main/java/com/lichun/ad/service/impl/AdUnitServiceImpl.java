package com.lichun.ad.service.impl;

import com.lichun.ad.constant.Constants;
import com.lichun.ad.dao.AdPlanRepository;
import com.lichun.ad.dao.AdUnitRepository;
import com.lichun.ad.dao.CreativeRepository;
import com.lichun.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.lichun.ad.dao.unit_condition.AdUnitItRepository;
import com.lichun.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.lichun.ad.dao.unit_condition.CreativeUnitRepository;
import com.lichun.ad.entity.AdPlan;
import com.lichun.ad.entity.AdUnit;
import com.lichun.ad.entity.unit_condition.AdUnitDistrict;
import com.lichun.ad.entity.unit_condition.AdUnitIt;
import com.lichun.ad.entity.unit_condition.AdUnitKeyword;
import com.lichun.ad.entity.unit_condition.CreativeUnit;
import com.lichun.ad.exception.AdException;
import com.lichun.ad.service.IAdUnitService;
import com.lichun.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private AdPlanRepository planRepository;
    private AdUnitRepository unitRepository;
    private AdUnitKeywordRepository unitKeywordRepository;
    private AdUnitItRepository unitItRepository;
    private AdUnitDistrictRepository unitDistrictRepository;
    private CreativeRepository creativeRepository;
    private CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository, AdUnitRepository unitRepository,
                             AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository,
                             AdUnitDistrictRepository unitDistrictRepository, CreativeRepository creativeRepository,
                             CreativeUnitRepository creativeUnitRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
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

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())) {
            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(), i.getKeyword())
            ));
            ids = unitKeywordRepository.saveAll(unitKeywords)
                    .stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIds().stream()
                .map(AdUnitItRequest.UnitId::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitIt> unitIts = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitIds())) {
            request.getUnitIds().forEach(i -> unitIts.add(
                    new AdUnitIt(i.getUnitId(), i.getItTag())
            ));
            ids = unitItRepository.saveAll(unitIts)
                    .stream()
                    .map(AdUnitIt::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistriceResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        List<Long> ids = Collections.emptyList();
        if (!CollectionUtils.isEmpty(request.getUnitDistricts())) {
            request.getUnitDistricts().forEach(i -> unitDistricts.add(
                    new AdUnitDistrict(i.getUnitId(), i.getProvince(), i.getCity())
            ));
            ids = unitDistrictRepository.saveAll(unitDistricts)
                    .stream()
                    .map(AdUnitDistrict::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitDistriceResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if (!(isRelatedUnitExist(unitIds) && isRelatedCreativeExist(creativeIds))) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(), i.getUnitId())
        ));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId).collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        return unitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }
        return creativeRepository.findAllById(creativeIds).size()
                == new HashSet<>(creativeIds).size();
    }

}
