package com.lichun.ad.service.impl;

import com.lichun.ad.dao.CreativeRepository;
import com.lichun.ad.entity.Creative;
import com.lichun.ad.service.ICreativeService;
import com.lichun.ad.vo.CreativeRequest;
import com.lichun.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreativeServiceImpl implements ICreativeService {

    private CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
