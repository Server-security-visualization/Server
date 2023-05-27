package com.dca.spring.src.weblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class WebLogList {
    private int webLogIdx;
    private String httpMethod;
    private String httpQuery;
    private String httpUrl;
    private int httpStatus;
    private double risk;
    private int level;
    private String time;
}
