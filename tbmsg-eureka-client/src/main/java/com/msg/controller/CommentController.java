package com.msg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.pojo.Tcomment;
import com.msg.service.AssistService;
import com.msg.service.TcommentService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    CommentController控制器
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:37
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:37
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
@RequestMapping("/")
public class CommentController {
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    TcommentService tcommentService;
    @Autowired
    AssistService assistService;

    /**
     * @param param
     * @return com.msg.pojo.ResultTemplate
     * @throws
     * @function 保存评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @ResponseBody
    @PostMapping("/xcx/comment")
    public ResultTemplate save(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR, "error");
        try {
            if (ObjectUtil.isJsonNoBlank(param)) {
                JSONObject data = param.getJSONObject("data");
                String token = param.getString("token");

                if (ObjectUtil.isJsonNoBlank(data)) {
                    Tcomment tcomment = new Tcomment();
                    String content = data.getString("content");
                    String urls = "";
                    if (ObjectUtil.isStringNoBlank(content)) {//评论内容不为空
                        Object urlObj = data.get("urls");
                        if (urlObj != null) {
                            List<String> urlList = (List<String>) urlObj;
                            if (urlList != null && urlList.size() > 0) {
                                for (int i = 0; i < urlList.size(); i++) {
                                    if (i != 0) {
                                        urls += ",";
                                    }
                                    urls += urlList.get(i);
                                }
                            }
                        }
                        tcomment.setContent(content);
                        tcomment.setUrls(urls);
                        tcomment.setCreateTime(DateFormatUtil.getTimeByDate(null));
                        tcomment.setOpenid(assistService.getOpenIdByToken(token));
                        boolean resultFlag = tcommentService.add(tcomment);
                        if (resultFlag == true) {
                            resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                            resultTemplate.setMessage("ok");
                            resultTemplate.setToken(token);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("保存评论信息异常", e);
        }
        return resultTemplate;
    }

    /**
     * @param param
     * @return com.msg.pojo.ResultTemplate
     * @throws
     * @function 查询评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @ResponseBody
    @PostMapping("/xcx/getCommentList")
    public ResultTemplate query(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_SUCCESS, "ok");
        try {
            if (ObjectUtil.isJsonNoBlank(param)&& ObjectUtil.isJsonNoBlank(param.getJSONObject("data"))) {
                JSONObject pageJson = param.getJSONObject("data");
                Map hashMap = new HashMap();
                Integer pageNumber = pageJson.getInteger("pageNum") != null&&pageJson.getInteger("pageNum")>0 ? pageJson.getInteger("pageNum") : 1;
                Integer pageSize = pageJson.getInteger("pageSize") != null ? pageJson.getInteger("pageSize") : 10;
                String token = param.getString("token");
                int startRow = (pageNumber-1)*pageSize;
                hashMap.put("startRow", startRow);
                hashMap.put("pageSize", pageSize);
                List<Map> tcommentList = tcommentService.query(hashMap);
                if (tcommentList != null && tcommentList.size() > 0) {//不为空时封装返回结果
                    JSONObject data = null;
                    Map<String, Object> map = null;
                    JSONArray dataArray = new JSONArray();
                    for (int i = 0; i < tcommentList.size(); i++) {
                        data = new JSONObject();
                        map = tcommentList.get(i);
                        //封装数据comment数据
                        JSONObject comment = new JSONObject();
                        comment.put("content", map.get("content"));
                        comment.put("createTime", map.get("createTime"));
                        comment.put("id", map.get("id"));
                        comment.put("productId", "");
                        comment.put("productId", map.get("productId"));
                        comment.put("userId", map.get("userId"));
                        data.put("comment", comment);
                        //封装数据user数据
                        JSONObject user = new JSONObject();
                        user.put("avatarUrl", map.get("avatarUrl"));
                        user.put("birthDay", map.get("birthDay"));
                        user.put("createTime", map.get("tuser_createTime"));
                        user.put("id", map.get("tuser_id"));
                        user.put("nickName", map.get("nickName"));
                        user.put("phone", map.get("phone"));
                        user.put("sex", map.get("sex"));
                        user.put("updateTime", map.get("tuser_updateTime"));
                        user.put("token", token);
                        data.put("user", user);
                        //封装数据urlsList数据
                        List<String> urlsList = new ArrayList<>();
                        String urls = (String)map.get("urls");
                        if(ObjectUtil.isStringNoBlank(urls))
                        {
                            String[] urlArr = urls.split(",");
                            if(urlArr!=null&&urlArr.length>0){
                                for (int k=0;k<urlArr.length;k++){
                                    urlsList.add(urlArr[k]);
                                }
                            }
                        }
                        data.put("urlsList", urlsList);
                        dataArray.add(data);
                    }
                    resultTemplate.setData(dataArray);
                }
            }
        } catch (Exception e) {
            logger.error("查询评论信息异常", e);
            resultTemplate.setCode(CodeUtil.RESULT_ERROR);
            resultTemplate.setMessage("error");
        }
        return resultTemplate;
    }

}
