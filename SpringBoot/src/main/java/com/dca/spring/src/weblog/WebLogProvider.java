package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebLogProvider {
    private final WebLogService webLogService;
    private final WebLogDao webLogDao;

    @Autowired
    public WebLogProvider(WebLogService webLogService, WebLogDao webLogDao) {
        this.webLogService = webLogService;
        this.webLogDao = webLogDao;
    }
}
