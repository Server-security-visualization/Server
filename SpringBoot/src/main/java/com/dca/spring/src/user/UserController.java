package com.dca.spring.src.user;

import com.dca.spring.config.BaseResponseStatus;
import com.dca.spring.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;

    @Autowired
    public UserController(UserProvider userProvider, UserService userService) {
        this.userProvider = userProvider;
        this.userService = userService;
    }

    /** 로그인
     * [POST]
     * /user/login
     **/
    @PostMapping("/login")
    public BaseResponse<UserLoginRes> loginCon(@RequestBody UserLoginReq userLoginReq){
        try{
            // ip or domain 입력하지 않았을 때
            if(userLoginReq.getIp() == null) {
                return new BaseResponse<>(BaseResponseStatus.POST_LOGIN_EMPTY_IP);
            }
            // password (uuid) 입력하지 않았을 때
            if(userLoginReq.getPwd() == null) {
                return new BaseResponse<>(BaseResponseStatus.POST_LOGIN_EMPTY_PWD);
            }

            UserLoginRes userLoginRes = userProvider.loginPro(userLoginReq);
            return new BaseResponse<>(userLoginRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}