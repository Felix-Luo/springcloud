package com.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.feign.pojo.ResultTemplate;
import com.feign.service.TbmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Description:    UsedController控制器
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:41
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:41
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
@RequestMapping("/")
public class UsedController {

    @Autowired
    TbmsgService tbmsgService;

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate
     * @throws
     * @function 上报用户使用时间
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/addUseTime")
    public ResultTemplate save(@RequestBody JSONObject param) {

        return tbmsgService.addUseTime(param);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate
     * @throws
     * @function 获取某一段时间内的使用次数
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/getPeriodUseTime")
    public ResultTemplate getPeriodUseTime(@RequestBody JSONObject param) {
        ResultTemplate resultTemplate = tbmsgService.getPeriodUseTime(param);
        return resultTemplate;
    }
}
