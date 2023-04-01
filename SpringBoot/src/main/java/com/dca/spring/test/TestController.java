package com.dca.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public TestController() {}

    @GetMapping("/server")
    public String test(){
        System.out.println("Success Test");
        return "Success Test";
    }
}
