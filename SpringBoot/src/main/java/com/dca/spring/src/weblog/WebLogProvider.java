package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.malware.model.MalList;
import com.dca.spring.src.malware.model.MalListRes;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dca.spring.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class WebLogProvider {
    private final WebLogService webLogService;
    private final WebLogDao webLogDao;

    @Autowired
    public WebLogProvider(WebLogService webLogService, WebLogDao webLogDao) {
        this.webLogService = webLogService;
        this.webLogDao = webLogDao;
    }

    /** WebLog List 조회 **/
    public WebLogListRes WebLogListPro() throws BaseException {
        try{
            List<WebLogList> webLogList = webLogDao.WebLogListDao();
            return new WebLogListRes(webLogList);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
