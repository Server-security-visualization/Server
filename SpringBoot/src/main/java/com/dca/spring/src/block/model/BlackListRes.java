package com.dca.spring.src.block.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BlackListRes {
    private List<BlockWebLogList> weblogBlacklist;
    private List<BlockMalwareList> malwareBlacklist;
}
