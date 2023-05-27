package com.dca.spring.src.block;

import com.dca.spring.src.block.model.*;
import com.dca.spring.src.block.*;
import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dca.spring.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/block")

public class BlockController {
    private final BlockProvider blockProvider;

    @Autowired
    public BlockController(BlockProvider blockProvider) {
        this.blockProvider = blockProvider;
    }
}
