package com.dca.spring.src.block.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class BlockList {
    private int blockIdx;
    private String time;
    private String ip;
}
