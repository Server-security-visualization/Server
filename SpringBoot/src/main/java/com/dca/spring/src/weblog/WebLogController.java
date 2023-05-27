package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/block")

public class WebLogController {
    private final WebLogProvider webLogProvider;
    private final WebLogService webLogService;

    @Autowired
    public WebLogController(WebLogProvider webLogProvider, WebLogService webLogService) {
        this.webLogProvider = webLogProvider;
        this.webLogService = webLogService;
    }
}
