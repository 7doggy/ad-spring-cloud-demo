package com.lichun.ad.dao;

import com.lichun.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    AdUser findAdUserByUsername(String username);
}
