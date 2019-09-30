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

import java.util.Map;

/**
* @Description:    SuggestionController控制器
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:37
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:37
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
@RequestMapping("/")
public class SuggestionController {

    @Autowired
    TbmsgService tbmsgService;
    /**
     * @function    保存意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:40
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/addSuggestion")
    public ResultTemplate save(@RequestBody JSONObject param){

        return tbmsgService.addSuggestion(param);
    }

    /**
     * @function    查询意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:41
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/query")
    public ResultTemplate query(@RequestBody JSONObject param){

        return tbmsgService.query(param);
    }
}
