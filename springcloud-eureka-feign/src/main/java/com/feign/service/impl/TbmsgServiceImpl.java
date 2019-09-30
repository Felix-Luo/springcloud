package com.feign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.feign.pojo.ResultTemplate;
import com.feign.service.TbmsgService;
import com.feign.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class TbmsgServiceImpl implements TbmsgService {

    @Value("${requestErrorMsg}")
    String requestErrorMsg;


    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 保存评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @Override
    public ResultTemplate comment(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "comment"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 查询评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @Override
    public ResultTemplate getCommentList(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "getCommentList"+requestErrorMsg);
    }

    /**
     * @param file
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 上传图片
     * @author luoyhong
     * @date 2019/6/26 18:39
     */
    @Override
    public ResultTemplate uploadImage(MultipartFile file) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "uploadImage"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 保存意见反馈信息
     * @author luoyhong
     * @date 2019/6/26 18:40
     */
    @Override
    public ResultTemplate addSuggestion(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "addSuggestion"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 查询意见反馈信息
     * @author luoyhong
     * @date 2019/6/26 18:41
     */
    @Override
    public ResultTemplate query(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "query"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 上报用户使用时间
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @Override
    public ResultTemplate addUseTime(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "addUseTime"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 获取某一段时间内的使用次数
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @Override
    public ResultTemplate getPeriodUseTime(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "getPeriodUseTime"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 登陆检验
     * @author luoyhong
     * @date 2019/6/26 18:43
     */
    @Override
    public ResultTemplate login(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "login"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 获取session key （免登）
     * @author luoyhong
     * @date 2019/6/27 10:12
     */
    @Override
    public ResultTemplate getAcessionKey(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "getAccessionKey"+requestErrorMsg);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 检验token是否有效
     * @author luoyhong
     * @date 2019/6/27 15:38
     */
    @Override
    public ResultTemplate tokenCheck(JSONObject param) {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "tokenCheck"+requestErrorMsg);
    }

    /**
     * @return com.feign.pojo.ResultTemplate;
     * @throws
     * @function 用于测试接口
     * @author luoyhong
     * @date 2019/6/27 15:38
     */
    @Override
    public ResultTemplate testData() {
        return new ResultTemplate(CodeUtil.RESULT_ERROR, "testData"+requestErrorMsg);
    }
}
