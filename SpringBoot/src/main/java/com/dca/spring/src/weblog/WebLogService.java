package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.dca.spring.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class WebLogService {
    private final WebLogDao webLogDao;

    @Autowired
    public WebLogService(WebLogDao webLogDao) {
        this.webLogDao = webLogDao;
    }

}
