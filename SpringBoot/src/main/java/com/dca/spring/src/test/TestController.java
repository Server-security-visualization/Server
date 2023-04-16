package com.dca.spring.src.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dca.spring.config.BaseException;
import com.dca.spring.config.BaseResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestProvider testProvider;

    @Autowired
    public TestController(TestProvider testProvider) {
        this.testProvider = testProvider;
    }

    @GetMapping("/server")
    public BaseResponse<String> serverTest(){
        System.out.println("Success Server Test");
        return new BaseResponse<>("Success Server Test");
    }

    @GetMapping("/mariadb")
    public BaseResponse<String> dbTest() throws BaseException {
        System.out.println("Success Server Test");
        String uuid = testProvider.getUuid();
        return new BaseResponse<>(uuid);
    }
}
