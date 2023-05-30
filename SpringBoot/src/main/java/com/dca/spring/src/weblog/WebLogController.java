package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.malware.model.MalListRes;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webLog")

public class WebLogController {
    private final WebLogProvider webLogProvider;
    private final WebLogService webLogService;

    @Autowired
    public WebLogController(WebLogProvider webLogProvider, WebLogService webLogService) {
        this.webLogProvider = webLogProvider;
        this.webLogService = webLogService;
    }

    /** WebLog List 조회
     * [GET]
     * /webLog/list
     **/
    @GetMapping("/list")
    public BaseResponse<WebLogListRes> WebLogList(){
        try{
            WebLogListRes webLogListRes = webLogProvider.WebLogListPro();
            return new BaseResponse<>(webLogListRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /** WebLog 상세보기
     * [GET]
     * /webLog/detail
     **/
    @GetMapping("/detail/{webLogIdx}")
    public BaseResponse<WebLogDetailRes> WebLogDetail(@PathVariable("webLogIdx") int webLogIdx){
        try{
            WebLogDetailRes webLogListRes = webLogProvider.WebLogDetailPro(webLogIdx);
            return new BaseResponse<>(webLogListRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
