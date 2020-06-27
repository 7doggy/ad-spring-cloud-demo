package com.lichun.ad.service;

import com.lichun.ad.vo.CreativeRequest;
import com.lichun.ad.vo.CreativeResponse;

public interface ICreativeService {
    CreativeResponse createCreative(CreativeRequest request);
}
