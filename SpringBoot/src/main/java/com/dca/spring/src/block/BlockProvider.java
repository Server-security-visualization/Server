package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dca.spring.config.BaseResponseStatus.*;

@Service
public class BlockProvider {
    private final BlockService blockService;
    private final BlockDao blockDao;

    @Autowired
    public BlockProvider(BlockService blockService, BlockDao blockDao) {
        this.blockService = blockService;
        this.blockDao = blockDao;
    }

    /** blacklist 조회 **/
    public BlackListRes BlackListPro() throws BaseException {
        try{
            BlackListRes blackListRes = blockDao.BlackListDao();
            return blackListRes;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
