package com.msg.controller;

import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.pojo.Tuser;
import com.msg.service.RedisService;
import com.msg.service.TuserService;
import com.msg.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:    UserController控制器
 * @Author:         luoyhong
 * @CreateDate:     2019/6/26 18:43
 * @UpdateUser:     luoyhong
 * @UpdateDate:     2019/6/26 18:43
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    TuserService tuserService;
    @Autowired
    RedisService redisService;
    @Value("${tokenValidDay}")
    long tokenValidDay;
     @Value("${appId}")
     String appId;
     @Value("${appSecret}")
     String appSecret;
    /**
     * @function    登陆检验
     * @author      luoyhong
     * @param param
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:43
     */
    @ResponseBody
    @PostMapping(value="/xcx/login")
    public ResultTemplate login(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"登录失败");
        try{
            if(param==null || param.isEmpty()){
                return resultTemplate;
            }
            //HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            //String token = request.getHeader("token");
            JSONObject data = param.getJSONObject("data");
            if(data==null||data.isEmpty()){
                return resultTemplate;
            }
            String encryptedData = data.getString("encryptedData");
            String sessionKey = data.getString("sessionKey");
            String iv = data.getString("iv");

            /*解密手机号，并获取绑定的appid*/
            String result = WxUtil.wxDecrypt(encryptedData, sessionKey, iv);
            JSONObject phoneJson = JSONObject.parseObject(result);

            JSONObject json = JSONObject.parseObject(result);
            if (json.containsKey("phoneNumber")&&json.getString("phoneNumber")!=null&&!"".equals(json.getString("phoneNumber"))) {

                String phone = json.getString("phoneNumber");
                String avatarUrl = data.get("avatarUrl")!=null?data.getString("avatarUrl"):"";
                String nickName = data.get("nickName")!=null?data.getString("nickName"):"";
                String birthDay = data.get("birthDay")!=null?data.getString("birthDay"):"";
                Integer sex = data.get("sex")!=null?data.getInteger("sex"):1;
                /*从redis缓存中获取openid*/
                String openid =  redisService.getByStringRedisTemplate(sessionKey);
                String token =  redisService.getByStringRedisTemplate(openid);

                /*封装入库数据*/
                Tuser tuser = new Tuser();
                tuser.setPhone(phone);
                tuser.setAvatarUrl(avatarUrl);
                tuser.setNickName(nickName);
                tuser.setBirthDay(birthDay);
                tuser.setSex(sex);
                tuser.setAppid(appId);
                tuser.setOpenid(openid);
                //更新用户信息
                JSONObject resultJson = updateDatabase(tuser);
                boolean status = resultJson.getBoolean("status");
                boolean loginFlag = resultJson.getBoolean("loginFlag");//true-第一次登录，false-第二次登录
                /*数据入库成功*/
                if(status){
                    resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                    resultTemplate.setMessage("ok");
                    Tuser tmp = (Tuser)resultJson.get("data");
                    if(tmp!=null){
                        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(tmp);
                        jsonObject.put("token",token);
                        JSONObject resultData = new JSONObject();
                        resultData.put("loginFlag",loginFlag);
                        resultData.put("user",jsonObject);
                        resultTemplate.setData(resultData);
                    }
                }
            }

        }catch (Exception e){
            logger.error("登录异常",e);
        }
        return resultTemplate;

    }

    /**
     * @function    获取session key （免登）
     * @author      luoyhong
     * @param param
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/27 10:12
     */
    @ResponseBody
    @PostMapping(value="/xcx/getAcessionKey")
    public ResultTemplate getAcessionKey(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"获取session key免登）失败");
        boolean flag = false;
        String sessionKey = null;
        String token = null;
        try{
            //解析请求参数
            JSONObject dataJson = param.getJSONObject("data");
            if(dataJson!=null&&dataJson.get("loginCode")!=null&&!"".equals(dataJson.getString("loginCode"))){
                //获取验证码
                String loginCode =  dataJson.getString("loginCode");
                JSONObject jsonObject = loginCheck(loginCode);
                // 我们需要的openid，在一个小程序中，openid是唯一的
                String openid = jsonObject.get("openid").toString();
                sessionKey = jsonObject.get("session_key").toString();
                String randomCode = UUID.randomUUID().toString().replaceAll("-","");
                //生成token
                //token = MD5Util.encoderByMd5(sessionKey+openid+randomCode);
                token = randomCode;
                //String value = sessionKey+"#"+openid+"#"+randomCode;
                //保存token并设置有效期
                redisService.setByStringRedisTemplate(openid,token,true,tokenValidDay, TimeUnit.DAYS);
                redisService.setByStringRedisTemplate(token,sessionKey,true,tokenValidDay, TimeUnit.DAYS);
                //保存openid并设置有效期
                redisService.setByStringRedisTemplate(sessionKey,openid,true,tokenValidDay, TimeUnit.DAYS);
                JSONObject result = new JSONObject();
                result.put("sessionKey",sessionKey);
                //封装结果
                resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                resultTemplate.setMessage("获取sessionKey成功");
                resultTemplate.setToken(token);
                resultTemplate.setData(result);
            }

        }catch (Exception e){
            logger.error("获取session key （免登）失败",e);
        }
        return resultTemplate;
    }

    /**
     * @function    检验token是否有效
     * @author      luoyhong
     * @param param
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/27 15:38
     */
    @ResponseBody
    @PostMapping(value="/xcx/tokenCheck")
    public ResultTemplate tokenCheck(@RequestBody  JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"token过期，需要重新登录");
        try{
            if(param!=null&&!param.isEmpty()){
                String token = param.getString("token");
                if(token!=null&&!"".equals(token)){
                    /*从数据库缓存获取token*/
                    String tokenValue =  redisService.getByStringRedisTemplate(token);
                    /*如果从数据库缓存获取token为空，则说明该token已经失效，验证失败*/
                    if(ObjectUtil.isStringNoBlank(tokenValue)){
                        resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                        resultTemplate.setMessage("token有效");
                    }
                }
            }
        }catch (Exception e){
            logger.error("检验token异常",e);
        }
        return resultTemplate;
    }

    /**
     * @function    调用微信登陆接口
     * @author      luoyhong
     * @param code
     * @return      com.alibaba.fastjson.JSONObject
     * @exception
     * @date        2019/7/9 17:19
     */
    public JSONObject loginCheck(String code) {

        JSONObject jsonObject = null;
        try{
            if((code!=null&&!"".equals(code))&&(appId!=null&&!"".equals(appId))&&(appSecret!=null&&!"".equals(appSecret))){
                // 根据小程序传过来的code想这个url发送请求
                String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
                // 发送请求，返回Json字符串
                String resultStr = HttpUtil.httpRequest(url, "GET", null);
                if(resultStr!=null&&!"".equals(resultStr)){
                    // 转成Json对象 获取openid
                    jsonObject = JSONObject.parseObject(resultStr);
                }

            }

        }catch (Exception e){
            logger.error("登录验证异常",e);
        }
        return jsonObject;
    }

    /**
     * @function    检查更新/添加
     * @author      luoyhong
     * @param tuser
     * @return      JSONObject
     * @exception
     * @date        2019/7/9 18:13
     */
    public JSONObject updateDatabase(Tuser tuser){
        JSONObject resultJson = new JSONObject();
        boolean resultFlag = true;
        boolean loginFlag = true;
        long nowTime = DateFormatUtil.getMillis(new Date());
        tuser.setCreateTime(nowTime);
        tuser.setUpdateTime(nowTime);
        /*删除用户信息（即更新，先删除，后添加，避免用户信息重复）*/
        int count = tuserService.delete(tuser);
        if(count>0){
            loginFlag = false;
        }
        /*添加用户信息*/
        resultFlag = tuserService.add(tuser);
        resultJson.put("status",resultFlag);
        resultJson.put("data",tuser);
        resultJson.put("loginFlag",loginFlag);
        return resultJson;
    }
}
