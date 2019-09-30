package com.msg.controller;

import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.utils.CodeUtil;
import com.msg.utils.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
* @Description:    文件操作控制器
* @Author:         luoyhong
* @CreateDate:     2019/6/25 11:16
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/25 11:16
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
@RequestMapping("/")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value(value="${localResourcePath}")
    String localResourcePath;
    @Value(value="${imageFolder}")
    String imageFolder;
    @Value(value="${localResourceUrlPath}")
    String localResourceUrlPath;

    /**
     * @function    上传图片
     * @author      luoyhong
     * @param file
     * @return      com.msg.pojo.ResultTemplate
     * @exception
     * @date        2019/6/26 18:39
     */
    @ResponseBody
    @PostMapping(value="/xcx/uploadImage",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultTemplate uploadImg(@RequestPart(value="file") MultipartFile file){

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR,"图片上传失败");
        boolean resultFlag = true;
        try {
            if(file!=null)
            {
                //String separator = File.separator;
                String separator = "/";
                String filename=file.getOriginalFilename();
                String[] tempA = filename.split("\\.");
                if(tempA!=null&&tempA.length>1){
                    String dateStr = DateFormatUtil.getDateStringByformat(new Date(), "yyyy-MM-dd");
                    if(dateStr==null||"".equals(dateStr)){
                        resultTemplate.setMessage("上传图片失败，获取日期失败");
                    }
                    String[] dateArr = dateStr.split("-");
                    if(dateArr==null||dateArr.length!=3){
                        resultTemplate.setMessage("上传图片失败，获取日期失败");
                    }
                    //拼接路径：年/月/日
                    String datePath = dateArr[0] + separator + dateArr[1] + separator + dateArr[2];
                    //保存图片的url相对路径：年/月/日 分开存贮
                    String imgRelationUrl = imageFolder + separator + datePath + separator + UUID.randomUUID().toString().replaceAll("-","") + "." + "png";
                    //String imgRelationUrl = imageFolder + File.separator + datePath + File.separator + UUID.randomUUID().toString().replaceAll("-","") + "." + tempA[1];
                    //保存图片的本地绝对路径
                    String imglocationPath = localResourcePath + separator + imgRelationUrl;
                    File picFile = new File(imglocationPath);
                    // 判断路径是否存在，不存在，则创建
                    if (!picFile.getParentFile().exists()) {
                        picFile.getParentFile().mkdirs();
                    }
                    //上传图片（保存到本地）
                    file.transferTo(picFile);

                    //图片url路径,web也页面通过访问该url显示图片
                    String imageUrl = separator + localResourceUrlPath + separator + imgRelationUrl;

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("iamgeUrl", imageUrl);
                    resultTemplate.setData(jsonObject);
                }else{
                    resultFlag = false;
                }

            }else{
                resultFlag = false;
            }
            if(resultFlag==true){
                resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                resultTemplate.setMessage("图片上传成功");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("上传图片异常",e);
        }
        return resultTemplate;
    }
}
