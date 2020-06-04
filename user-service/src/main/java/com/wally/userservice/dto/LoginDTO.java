package com.wally.userservice.dto;

import com.wally.userservice.entity.User;
import lombok.Data;

@Data
public class LoginDTO {
    private User user;
    private String token;
}
