package com.lichun.ad.sponsor.service.impl;

import com.lichun.ad.sponsor.constant.Constants;
import com.lichun.ad.sponsor.dao.AdUserRepository;
import com.lichun.ad.sponsor.entity.AdUser;
import com.lichun.ad.sponsor.exception.AdException;
import com.lichun.ad.sponsor.service.IUserService;
import com.lichun.ad.sponsor.vo.CreateUserRequest;
import com.lichun.ad.sponsor.vo.CreateUserResponse;
import com.lichun.ad.sponsor.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private final AdUserRepository userRepository;
    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = userRepository.findAdUserByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));
        return new CreateUserResponse(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getToken(),
                newUser.getCreateTime(),
                newUser.getUpdateTime()
        );
    }
}
