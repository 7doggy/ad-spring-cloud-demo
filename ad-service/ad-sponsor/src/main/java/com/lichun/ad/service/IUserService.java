package com.lichun.ad.service;

import com.lichun.ad.exception.AdException;
import com.lichun.ad.vo.CreateUserRequest;
import com.lichun.ad.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
