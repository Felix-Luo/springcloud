package com.feign.controller;

import com.feign.service.EurekaFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class EurekaFeignController {

    @Resource
    private EurekaFeignService eurekaFeignService;

    @RequestMapping("/feignInfo")
    public String feignInfo() {
        String message = eurekaFeignService.getInfo();
        return "获取到的信息:" + message;
    }
    @RequestMapping("/feignTestInfo")
    public String testInfo(HttpServletRequest request) {
        String message = eurekaFeignService.getTestInfo();
        return "获取到的信息:" + message;
    }

}
