package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import com.dca.spring.src.malware.model.MalList;
import com.dca.spring.src.malware.model.MalListRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.dca.spring.config.BaseResponseStatus.*;

@Service
public class BlockService {
    private final BlockDao blockDao;

    @Autowired
    public BlockService(BlockDao blockDao) {
        this.blockDao = blockDao;
    }

    /** IP 차단 **/
    public BlockRes BlockIpSer(BlockReq blockReq) throws BaseException {
        try{
            int blockIdx = blockDao.BlockIpDao(blockReq);
            return new BlockRes(blockIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
