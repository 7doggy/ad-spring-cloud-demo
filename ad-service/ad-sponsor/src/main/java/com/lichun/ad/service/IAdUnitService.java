package com.lichun.ad.service;

import com.lichun.ad.exception.AdException;
import com.lichun.ad.vo.*;

public interface IAdUnitService {
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistriceResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;
}
