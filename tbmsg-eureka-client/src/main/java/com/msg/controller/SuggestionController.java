package com.msg.controller;

import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.pojo.Tsuggestion;
import com.msg.service.AssistService;
import com.msg.service.TsuggestionService;
import com.msg.utils.CodeUtil;
import com.msg.utils.DateFormatUtil;
import com.msg.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    private final static Logger logger = LoggerFactory.getLogger(SuggestionController.class);
    @Autowired
    TsuggestionService tsuggestionService;
    @Autowired
    AssistService assistService;
    /**
     * @function    保存意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:40
     */
    @ResponseBody
    @PostMapping("/xcx/addSuggestion")
    public ResultTemplate save(@RequestBody JSONObject param){

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"fail");
        try{
            if(ObjectUtil.isJsonNoBlank(param)){
                JSONObject data = param.getJSONObject("data");
                String token = param.getString("token");
                if(ObjectUtil.isJsonNoBlank(data)){
                    String content = data.getString("content");
                    if(ObjectUtil.isStringNoBlank(content)){
                        Tsuggestion tsuggestion = new Tsuggestion();
                        tsuggestion.setContent(content);
                        tsuggestion.setOpenid(assistService.getOpenIdByToken(token));
                        tsuggestion.setCreateTime(DateFormatUtil.getTimeByDate(null));
                        boolean resultFlag = tsuggestionService.add(tsuggestion);
                        if(resultFlag==true){
                            resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                            resultTemplate.setMessage("ok");
                            resultTemplate.setToken(token);
                        }
                    }

                }
            }
        }catch (Exception e){
            logger.error("保存反馈意见异常",e);
        }
        return resultTemplate;
    }

    /**
     * @function    查询意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:41
     */
    @ResponseBody
    @PostMapping("/xcx/query")
    public ResultTemplate query(@RequestBody JSONObject param){

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"查询失败");
        try{
            if(param!=null){
                List<Tsuggestion> tsuggestionList = tsuggestionService.query(param);
                resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                resultTemplate.setMessage("查询成功");
                resultTemplate.setData(tsuggestionList);
            }
        }catch (Exception e){
            logger.error("查询异常",e);
        }
        return resultTemplate;
    }
}
