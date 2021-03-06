package com.lichun.ad.search.client;

import com.lichun.ad.search.client.vo.AdPlan;
import com.lichun.ad.search.client.vo.AdPlanGetRequest;
import com.lichun.ad.sponsor.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @PostMapping("/ad-sponsor/get/adPlan")
    CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request
    );

}
