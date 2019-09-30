package com.msg.service;

import com.msg.pojo.Tuser;

import java.util.List;
import java.util.Map;

/**
* @Description:    TuserService接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 13:42
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 13:42
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface TuserService {
    boolean add(Tuser tuser);
    int delete(Tuser tuser);
    List<Tuser> query(Map param);
    boolean update(Tuser tuser);
}
