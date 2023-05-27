package com.dca.spring.src.block.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockReq {
    private String ip;
    private int type; // 1: malware / 2: web_log / 3: system_log
    private int infoIdx;
}
