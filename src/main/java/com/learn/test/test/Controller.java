package com.learn.test.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @GetMapping(value="/testAPIs")
    public String getMethodName() {
        return "200";
    }


}
