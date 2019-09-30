package com.msg.service;

import com.msg.pojo.Tused;

import java.util.List;
import java.util.Map;

/**
* @Description:    TusedService接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 17:36
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 17:36
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface TusedService {
    boolean add(Tused tused);
    List<Tused> query(Map param);
    List<Map> countByTime(Map param);
}
