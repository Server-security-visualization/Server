package com.dca.spring.src.user;

import com.dca.spring.config.BaseException;
import com.dca.spring.src.user.model.*;
import com.dca.spring.src.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dca.spring.config.BaseResponseStatus.*;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
