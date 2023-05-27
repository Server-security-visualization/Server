package com.dca.spring.src.block.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockRes {
    private int blockIdx; // 추가된 block index

    public BlockRes(){}

    public BlockRes(int blockIdx){
        this.blockIdx = blockIdx;
    }
}
