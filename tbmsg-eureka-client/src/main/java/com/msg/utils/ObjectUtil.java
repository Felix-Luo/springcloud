package com.msg.utils;

import com.alibaba.fastjson.JSONObject;

/**
* @Description:    字符工具类
* @Author:         luoyhong
* @CreateDate:     2019/7/11 10:52
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/7/11 10:52
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ObjectUtil {
    public static boolean isStringNoBlank(String str){
        if(str!=null&&!"".equals(str)){
            return true;
        }
        return false;
    }
    public static boolean isJsonNoBlank(JSONObject jsonObject){
        if(jsonObject!=null&&!jsonObject.isEmpty()){
            return true;
        }
        return false;
    }

}
