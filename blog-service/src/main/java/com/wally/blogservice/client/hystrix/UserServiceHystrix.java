package com.wally.blogservice.client.hystrix;

import com.wally.blogservice.client.UserServiceClient;
import com.wally.blogservice.entity.User;
import com.wally.common.dto.RespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceHystrix implements UserServiceClient {


    @Override
    public RespDTO<User> getUser(String token, String userName) {
        log.info("token={}",token);
        log.info("userName={}",userName);
        return null;
    }
}
