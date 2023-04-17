package com.dca.spring.src.user;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponseStatus;
import com.dca.spring.src.user.model.*;
import com.dca.spring.src.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dca.spring.config.BaseResponseStatus.*;

@Service
public class UserProvider {
    private final UserService userService;
    private final UserDao userDao;

    @Autowired
    public UserProvider(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    /** 로그인 **/
    @Transactional
    public UserLoginRes loginPro(UserLoginReq userLoginReq) throws BaseException{
        try{
            boolean check = userDao.checkPwd(userLoginReq.getPwd());
            return new UserLoginRes(check);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
