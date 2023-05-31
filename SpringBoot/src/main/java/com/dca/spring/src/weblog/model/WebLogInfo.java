package com.dca.spring.src.weblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WebLogInfo {
    private String ip;
    private String httpMethod;
    private String httpQuery;
    private String httpUrl;
    private int httpStatus;
    private int pktBytes;
    private int rcvdBytes;
    private int sentBytes;
    private String referer;
    private double risk;
    private String time;
}
