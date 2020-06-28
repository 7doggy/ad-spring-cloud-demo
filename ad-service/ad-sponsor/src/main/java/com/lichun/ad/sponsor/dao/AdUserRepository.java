package com.lichun.ad.sponsor.dao;

import com.lichun.ad.sponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {
    AdUser findAdUserByUsername(String username);
}
