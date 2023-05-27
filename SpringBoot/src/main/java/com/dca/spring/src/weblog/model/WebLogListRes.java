package com.dca.spring.src.weblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class WebLogListRes {
    private List<WebLogList> webLogList;
}
