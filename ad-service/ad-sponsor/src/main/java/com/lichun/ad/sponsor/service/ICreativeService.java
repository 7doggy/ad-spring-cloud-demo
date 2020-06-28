package com.lichun.ad.sponsor.service;

import com.lichun.ad.sponsor.vo.CreativeRequest;
import com.lichun.ad.sponsor.vo.CreativeResponse;

public interface ICreativeService {
    CreativeResponse createCreative(CreativeRequest request);
}
