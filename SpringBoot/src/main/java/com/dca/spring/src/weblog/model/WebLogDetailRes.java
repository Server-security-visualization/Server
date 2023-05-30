package com.dca.spring.src.weblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class WebLogDetailRes {
    private int webLogIdx;
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
    private int level;
    private String time;
}
