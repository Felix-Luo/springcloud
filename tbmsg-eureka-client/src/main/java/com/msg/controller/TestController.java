package com.msg.controller;

import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.pojo.Tuser;
import com.msg.service.AsyncService;
import com.msg.service.RedisService;
import com.msg.service.TuserService;
import com.msg.utils.CodeUtil;
import com.msg.utils.DateFormatUtil;
import com.msg.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    RedisService redisService;
    @Autowired
    AsyncService asyncService;
    @Autowired
    TuserService tuserService;

    @PostMapping("/test/setRedis")
    @ResponseBody
    public ResultTemplate testRedis(@RequestBody JSONObject param){

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"数据保存到redis失败");
        try{
            String key = param.getString("key");
            String value = param.getString("value");
            redisService.setByStringRedisTemplate(key,value,true,60, TimeUnit.SECONDS);
            String myval =  redisService.getByStringRedisTemplate(key);
            System.out.println("value:"+myval);
            resultTemplate.setData(myval);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return resultTemplate;

    }
    @PostMapping("/test/getRedis")
    @ResponseBody
    public ResultTemplate getRedisData(@RequestBody JSONObject param){
        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"从redis获取数据失败");
        try{
            String key = param.getString("key");
            String myval =  redisService.getByStringRedisTemplate(key);
            resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
            resultTemplate.setMessage(myval);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultTemplate;

    }
    @GetMapping("/test/testIndex")
    @ResponseBody
    public ResultTemplate testIndex(){
        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_SUCCESS,"成功");
        //调用service层的任务
        //asyncService.executeAsync();
        return resultTemplate;
    }
    @PostMapping("/test/addUser")
    @ResponseBody
    public ResultTemplate addUser(@RequestBody JSONObject param, HttpServletRequest request){
        /*
        {"phoneNumber":"phoneNumber2","avatarUrl":"avatarUrl2","nickName":"nickName2","birthDay":"birthDay2","sex":0,"appId":"appId2","openid":"openid2"}
        * */
        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"error");
            String phone = param.getString("phoneNumber");
            String avatarUrl = param.get("avatarUrl")!=null?param.getString("avatarUrl"):"";
            String nickName = param.get("nickName")!=null?param.getString("nickName"):"";
            String birthDay = param.get("birthDay")!=null?param.getString("birthDay"):"";
            Integer sex = param.get("sex")!=null?param.getInteger("sex"):1;
            String appId = param.get("appId")!=null?param.getString("appId"):"";
            String openid = param.get("openid")!=null?param.getString("openid"):"";

            /*封装入库数据*/
            Tuser tuser = new Tuser();
            tuser.setPhone(phone);
            tuser.setAvatarUrl(avatarUrl);
            tuser.setNickName(nickName);
            tuser.setBirthDay(birthDay);
            tuser.setSex(sex);
            tuser.setAppid(appId);
            long nowTime = DateFormatUtil.getMillis(new Date());
            tuser.setCreateTime(nowTime);
            tuser.setUpdateTime(nowTime);
            tuser.setOpenid(openid);

            boolean resultFlag = tuserService.add(tuser);
            String token = request.getHeader("token");
            return resultTemplate;
    }
    @PostMapping("/test/testData")
    @ResponseBody
    public ResultTemplate testData(){
        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_SUCCESS,"ok");
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        JSONObject jsonObject = HttpUtil.getServerInfo(request);
        resultTemplate.setData(jsonObject);
        resultTemplate.setToken(token);
        return resultTemplate;
    }
}
