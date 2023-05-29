package com.dca.spring.src.block.model;

import com.dca.spring.src.malware.model.MalList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BlackListRes {
    private List<BlockWebList> weblogBlacklist;
    private List<BlockMalList> malwareBlacklist;
}
