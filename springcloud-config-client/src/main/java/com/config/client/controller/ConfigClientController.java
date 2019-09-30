package com.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 3nod on 2019/6/28.
 */
@Controller
public class ConfigClientController {
    @Value("${info}")
    private String info;
    //private String info = "local-config-client-dev";

    /**
     * 提供的一个restful服务
     *
     * @return 返回  配置中的info信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        return info;
    }
}
