package com.wally.userservice.service;

import com.wally.common.dto.RespDTO;
import com.wally.common.exception.CommonException;
import com.wally.common.exception.ErrorCode;
import com.wally.userservice.client.AuthServiceClient;
import com.wally.userservice.dao.UserDao;
import com.wally.userservice.dto.LoginDTO;
import com.wally.userservice.entity.JWT;
import com.wally.userservice.entity.User;
import com.wally.userservice.utils.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Resource(name = "authServiceHystrix")
    private AuthServiceClient authServiceClient;

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User getUserInfo(String userName) {
        return userDao.findByUserName(userName);
    }

    public RespDTO login(String userName, String passWord) {
        User user = userDao.findByUserName(userName);
//        if(null==user){
//            throw new CommonException(ErrorCode.USER_NOT_FOUND);
//        }
        Assert.isNull(user, "没有对应的用户");
        if (!BPwdEncoderUtils.matches(passWord, user.getPassWord())){
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }

        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", userName, passWord);
        // 获得用户菜单
        if(null==jwt){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }

}
