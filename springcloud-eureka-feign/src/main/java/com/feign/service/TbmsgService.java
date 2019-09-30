package com.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.feign.pojo.ResultTemplate;
import com.feign.service.impl.TbmsgServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "TBMSG-EUREKA-CLIENT", fallback = TbmsgServiceImpl.class) // 调用的服务的名称
//@FeignClient(value = "TBMSG-EUREKA-CLIENT", fallback = TbmsgServiceImpl.class,configuration = FeignConfiguration.class) // 调用的服务的名称
public interface TbmsgService {
    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 保存评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @PostMapping("tbmsg/xcx/comment")
    ResultTemplate comment(@RequestBody JSONObject param);

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 查询评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @PostMapping("tbmsg/xcx/getCommentList")
    ResultTemplate getCommentList(@RequestBody JSONObject param);

    /**
     * @function    上传图片
     * @author      luoyhong
     * @param file
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/26 18:39
     */
    @PostMapping("tbmsg/xcx/uploadImage")
    ResultTemplate uploadImage(@RequestPart(value = "file") MultipartFile file);

    /**
     * @function    保存意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/26 18:40
     */
    @PostMapping("tbmsg/xcx/addSuggestion")
    ResultTemplate addSuggestion(@RequestBody JSONObject param);

    /**
     * @function    查询意见反馈信息
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/26 18:41
     */
    @PostMapping("tbmsg/xcx/query")
    ResultTemplate query(@RequestBody JSONObject param);

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 上报用户使用时间
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @PostMapping("tbmsg/xcx/addUseTime")
    ResultTemplate addUseTime(@RequestBody JSONObject param);

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 获取某一段时间内的使用次数
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @PostMapping("tbmsg/xcx/getPeriodUseTime")
    ResultTemplate getPeriodUseTime(@RequestBody JSONObject param);

    /**
     * @function    登陆检验
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/26 18:43
     */
    @PostMapping(value="tbmsg/xcx/login")
    ResultTemplate login(@RequestBody JSONObject param);

    /**
     * @function    获取session key （免登）
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/27 10:12
     */
    @PostMapping(value="tbmsg/xcx/getAcessionKey")
    ResultTemplate getAcessionKey(@RequestBody JSONObject param);

    /**
     * @function    检验token是否有效
     * @author      luoyhong
     * @param param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/27 15:38
     */
    @PostMapping(value="tbmsg/xcx/tokenCheck")
    ResultTemplate tokenCheck(@RequestBody JSONObject param);
    /**
     * @function    用于测试接口
     * @author      luoyhong
     * @param
     * @return      com.feign.pojo.ResultTemplate;
     * @exception
     * @date        2019/6/27 15:38
     */
    @PostMapping(value="tbmsg/test/testData")
    ResultTemplate testData();
}
