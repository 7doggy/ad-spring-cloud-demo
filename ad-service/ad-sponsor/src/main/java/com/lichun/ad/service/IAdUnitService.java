package com.lichun.ad.service;

import com.lichun.ad.exception.AdException;
import com.lichun.ad.vo.AdUnitRequest;
import com.lichun.ad.vo.AdUnitResponse;

public interface IAdUnitService {
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;
}
