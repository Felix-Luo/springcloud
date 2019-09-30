package com.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.feign.pojo.ResultTemplate;
import com.feign.service.TbmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    TbmsgService tbmsgService;

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate
     * @throws
     * @function 保存评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/comment")
    public ResultTemplate save(@RequestBody JSONObject param) {
        return tbmsgService.comment(param);
    }

    /**
     * @param param
     * @return com.feign.pojo.ResultTemplate
     * @throws
     * @function 查询评论信息
     * @author luoyhong
     * @date 2019/6/26 18:38
     */
    @ResponseBody
    @PostMapping("tbmsg/xcx/getCommentList")
    public ResultTemplate getCommentList(@RequestBody JSONObject param) {

        return tbmsgService.getCommentList(param);
    }

    /**
     * @function    上传图片
     * @author      luoyhong
     * @param file
     * @return      com.feign.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:39
     */
    @ResponseBody
    @PostMapping(value="tbmsg/xcx/uploadImage",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultTemplate uploadImg(@RequestPart(value = "file") MultipartFile file){

        return tbmsgService.uploadImage(file);
    }

}
