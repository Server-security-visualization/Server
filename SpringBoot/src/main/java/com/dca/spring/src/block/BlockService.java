package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dca.spring.config.BaseResponseStatus.*;

public class BlockService {
    private final BlockDao blockDao;

    @Autowired
    public BlockService(BlockDao blockDao) {
        this.blockDao = blockDao;
    }
}
