package com.dca.spring.src.weblog;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.malware.model.MalInfo;
import com.dca.spring.src.malware.model.MalListRes;
import com.dca.spring.src.weblog.*;
import com.dca.spring.src.weblog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /** WebLog CSV 파일 생성
     * [Post]
     * /webLog/download
     **/
    @PostMapping("/download")
    public ResponseEntity<String> DownloadCSV() throws BaseException {
        List<WebLogInfo> webLogInfoList = webLogProvider.GetWebLogInfo();

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=MS949");
        header.add("Content-Disposition", "attachment; filename=weblog_info.csv");

        String data = "";
        data += "ip, http_method, http_query, http_url, http_status, pkt_bytes, " +
                "rcvd_bytes, sent_bytes, referer, risk, time\n";

        for(int i=0; i<webLogInfoList.size(); i++){
            data += webLogInfoList.get(i).getIp()+",";
            data += webLogInfoList.get(i).getHttpMethod()+",";
            data += webLogInfoList.get(i).getHttpQuery()+",";
            data += webLogInfoList.get(i).getHttpUrl()+",";
            data += webLogInfoList.get(i).getHttpStatus()+",";
            data += webLogInfoList.get(i).getPktBytes()+",";
            data += webLogInfoList.get(i).getRcvdBytes()+",";
            data += webLogInfoList.get(i).getSentBytes()+",";
            data += webLogInfoList.get(i).getReferer()+",";
            data += webLogInfoList.get(i).getRisk()+",";
            data += webLogInfoList.get(i).getTime()+"\n";
        }

        return new ResponseEntity<String>(data, header, HttpStatus.CREATED);
    }
}
