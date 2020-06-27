package com.lichun.ad.controller;

import com.alibaba.fastjson.JSON;
import com.lichun.ad.exception.AdException;
import com.lichun.ad.service.ICreativeService;
import com.lichun.ad.vo.CreativeRequest;
import com.lichun.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreativeOpController {
    private ICreativeService creativeService;
    @Autowired
    public CreativeOpController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        log.info("ad-sponsor:createCreative -> {}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }

}
