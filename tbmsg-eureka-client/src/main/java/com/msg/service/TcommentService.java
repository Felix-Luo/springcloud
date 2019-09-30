package com.msg.service;

import com.msg.pojo.Tcomment;

import java.util.List;
import java.util.Map;

/**
* @Description:    TcommentService接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 13:42
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 13:42
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface TcommentService {
    boolean add(Tcomment tcomment);
    boolean delete(Tcomment tcomment);
    List<Map> query(Map param);
    boolean update(Tcomment tcomment);
}
