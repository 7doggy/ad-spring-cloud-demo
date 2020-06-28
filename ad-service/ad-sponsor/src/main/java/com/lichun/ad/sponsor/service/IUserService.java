package com.lichun.ad.sponsor.service;

import com.lichun.ad.sponsor.exception.AdException;
import com.lichun.ad.sponsor.vo.CreateUserRequest;
import com.lichun.ad.sponsor.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
