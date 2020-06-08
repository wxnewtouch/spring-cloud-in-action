package com.wally.blogservice.service;

import com.wally.blogservice.client.UserServiceClient;
import com.wally.blogservice.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private UserServiceClient userServiceClient;
}
