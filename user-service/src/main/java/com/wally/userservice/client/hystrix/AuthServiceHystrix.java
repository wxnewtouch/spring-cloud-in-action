package com.wally.userservice.client.hystrix;

import com.wally.userservice.client.AuthServiceClient;
import com.wally.userservice.entity.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        log.info("--------ops getToken hystrix---------");
        return null;
    }
}
