package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import com.dca.spring.src.malware.model.MalListRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dca.spring.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/block")

public class BlockController {
    private final BlockProvider blockProvider;
    private final BlockService blockService;

    @Autowired
    public BlockController(BlockProvider blockProvider, BlockService blockService) {
        this.blockProvider = blockProvider;
        this.blockService = blockService;
    }

    /** IP 차단
     * [POST]
     * /block
     **/
    @PostMapping
    public BaseResponse<BlockRes> BlockIp(@RequestBody BlockReq blockReq){
        try{
            BlockRes blockRes = blockService.BlockIpSer(blockReq);
            return new BaseResponse<>(blockRes);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /** blackList 조회
     * [GET]
     * /block/blacklist
     **/
    @GetMapping("/blacklist")
    public BaseResponse<BlackListRes> BlackList(){
        try{
            BlackListRes blackListRes = blockProvider.BlackListPro();
            return new BaseResponse<>(blackListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
