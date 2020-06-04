package com.wally.uaaservice.dao;

import com.wally.uaaservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
