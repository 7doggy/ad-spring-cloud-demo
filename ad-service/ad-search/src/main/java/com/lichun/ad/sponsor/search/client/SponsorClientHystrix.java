package com.lichun.ad.sponsor.search.client;

import com.lichun.ad.sponsor.search.client.vo.AdPlan;
import com.lichun.ad.sponsor.search.client.vo.AdPlanGetRequest;
import com.lichun.ad.sponsor.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient{
    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }

}
