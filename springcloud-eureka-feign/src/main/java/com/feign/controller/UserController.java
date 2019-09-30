package com.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.feign.pojo.ResultTemplate;
import com.feign.service.TbmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    TbmsgService tbmsgService;
    /**
     * @function    登陆检验
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:43
     */
    @ResponseBody
    @PostMapping(value="tbmsg/xcx/login")
    public ResultTemplate login(@RequestBody JSONObject param) {
        return tbmsgService.login(param);
    }

    /**
     * @function    获取session key （免登）
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/27 10:12
     */
    @ResponseBody
    @PostMapping(value="tbmsg/xcx/getAcessionKey")
    public ResultTemplate getAcessionKey(@RequestBody JSONObject param) {
        return tbmsgService.getAcessionKey(param);
    }

    /**
     * @function    检验token是否有效
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/27 15:38
     */
    @ResponseBody
    @PostMapping(value="tbmsg/xcx/tokenCheck")
    public ResultTemplate tokenCheck(@RequestBody  JSONObject param) {
        return tbmsgService.tokenCheck(param);
    }

    /**
     * @function    用于测试接口
     * @author      luoyhong
     * @param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/27 15:38
     */
    @ResponseBody
    @PostMapping(value="tbmsg/xcx/testData")
    public ResultTemplate testData() {
        return tbmsgService.testData();
    }

}
