package com.wally.userservice.dao;

import com.wally.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
