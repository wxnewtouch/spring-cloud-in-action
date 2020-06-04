package com.wally.uaaservice.service;

import com.wally.uaaservice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDao.findByUserName(userName);
    }
}
