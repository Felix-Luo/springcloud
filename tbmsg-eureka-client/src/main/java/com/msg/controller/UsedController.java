package com.msg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.msg.pojo.ResultTemplate;
import com.msg.pojo.Tused;
import com.msg.service.AssistService;
import com.msg.service.TusedService;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final static Logger logger = LoggerFactory.getLogger(UsedController.class);
    @Autowired
    TusedService tusedService;
    @Autowired
    AssistService assistService;

    /**
     * @param param
     * @return com.msg.pojo.ResultTemplate
     * @throws
     * @function 上报用户使用时间
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @ResponseBody
    @PostMapping("/xcx/addUseTime")
    public ResultTemplate save(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR, "error");
        try {
            if (ObjectUtil.isJsonNoBlank(param)) {
                JSONObject data = param.getJSONObject("data");
                String token = param.getString("token");
                if (ObjectUtil.isJsonNoBlank(data)) {
                    String time = data.getString("time");
                    long useTime = DateFormatUtil.formatTime(time);
                    Date date = DateFormatUtil.getDateByFormat(time,"yyyy-MM-dd HH:mm:ss");
                    time = DateFormatUtil.formatTime(date,"yyyy-MM-dd");
                    String duration = data.getString("duration");
                    String pointType = data.getString("pointType");
                    Tused tused = new Tused();
                    tused.setOpenid(assistService.getOpenIdByToken(token));
                    tused.setTime(time);
                    tused.setUseTime(useTime);
                    tused.setDuration(duration);
                    tused.setPointType(pointType);
                    tused.setCreateTime(DateFormatUtil.getTimeByDate(null));
                    boolean resultFlag = tusedService.add(tused);

                    if (resultFlag == true) {
                        resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                        resultTemplate.setMessage("ok");
                        resultTemplate.setToken(token);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("上报用户使用时间异常", e);
        }
        return resultTemplate;
    }

    /**
     * @param param
     * @return com.msg.pojo.ResultTemplate
     * @throws
     * @function 获取某一段时间内的使用次数
     * @author luoyhong
     * @date 2019/6/26 18:42
     */
    @ResponseBody
    @PostMapping("/xcx/getPeriodUseTime")
    public ResultTemplate getPeriodUseTime(@RequestBody JSONObject param) {

        ResultTemplate resultTemplate = new ResultTemplate(CodeUtil.RESULT_ERROR, "error");
        try {
            if (ObjectUtil.isJsonNoBlank(param)) {
                JSONObject data = param.getJSONObject("data");
                String token = param.getString("token");
                if (ObjectUtil.isJsonNoBlank(data)) {
                    String startTime = data.getString("startTime")!=null?data.getString("startTime"):null;
                    String endTime = data.getString("endTime")!=null?data.getString("endTime"):null;
                    String pointType = data.getString("pointType")!=null?data.getString("pointType"):null;

                    Map<String,String> hashMap = new HashMap<>();
                    hashMap.put("startTime",startTime);
                    hashMap.put("endTime",endTime);
                    hashMap.put("pointType",pointType);

                    /*按时间段查询统计*/
                    List<Map> mapList = tusedService.countByTime(hashMap);
                    JSONArray dataJSONArray = new JSONArray();
                    if(mapList!=null&&mapList.size()>0){
                        Map<String,Object> queryMap = new HashMap<>();
                        queryMap.put("pointType",pointType);
                        JSONObject dataJson = null;
                        for(int i=0;i<mapList.size();i++){
                            Map<String,String> map = mapList.get(i);
                            queryMap.put("time",map.get("time"));
                            List<Tused> tusedList = tusedService.query(queryMap);
                            dataJson = new JSONObject();
                            dataJson.put("list",tusedList);
                            dataJson.put("map",map);
                            dataJSONArray.add(dataJson);
                        }
                    }

                    resultTemplate.setCode(CodeUtil.RESULT_SUCCESS);
                    resultTemplate.setMessage("ok");
                    resultTemplate.setToken(token);
                    resultTemplate.setData(dataJSONArray);
                }
            }
        }catch (Exception e){
            logger.error("获取某一段时间内的使用次数异常",e);
        }
        return resultTemplate;
    }
}
