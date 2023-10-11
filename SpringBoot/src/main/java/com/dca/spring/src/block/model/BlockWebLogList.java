package com.dca.spring.src.block.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class BlockWebLogList {
    private int blockIdx;
    private String time;
    private int webLogIdx;
    private String ip;
    private String httpMethod;
    private String httpQuery;
    private String httpUrl;
    private int httpStatus;
    private double risk;
}
