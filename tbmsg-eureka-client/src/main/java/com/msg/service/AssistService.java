package com.msg.service;

/**
* @Description:    辅助接口
* @Author:         luoyhong
* @CreateDate:     2019/7/11 11:17
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/7/11 11:17
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface AssistService {
    String getOpenIdByToken(String token);
    boolean checkToken(String token);
}
