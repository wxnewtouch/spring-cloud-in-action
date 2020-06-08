package com.wally.blogservice.client;

import com.wally.blogservice.client.hystrix.UserServiceHystrix;
import com.wally.blogservice.entity.User;
import com.wally.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", fallback = UserServiceHystrix.class)
public interface UserServiceClient {
    @PostMapping("/user/{userName}")
    RespDTO<User> getUser(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "userName") String userName);
}
