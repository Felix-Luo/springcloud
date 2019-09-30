package com.msg.service;

import com.msg.pojo.Tsuggestion;

import java.util.List;
import java.util.Map;

/**
* @Description:    TsuggestionService接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:30
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:30
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface TsuggestionService {
    public boolean add(Tsuggestion tsuggestion);
    public List<Tsuggestion> query(Map param);
}
