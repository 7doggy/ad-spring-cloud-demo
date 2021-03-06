package com.lichun.ad.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.lichun.ad.sponsor.exception.AdException;
import com.lichun.ad.sponsor.service.IUserService;
import com.lichun.ad.sponsor.vo.CreateUserRequest;
import com.lichun.ad.sponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserOpController {

    private IUserService userService;

    @Autowired
    public UserOpController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor:createUser -> {}",
                JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
