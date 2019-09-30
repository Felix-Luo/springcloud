package com.msg.service.impl;

import com.msg.service.AssistService;
import com.msg.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @Description:    辅助接口实现类
* @Author:         luoyhong
* @CreateDate:     2019/7/11 11:19
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/7/11 11:19
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Transactional
@Service
public class AssistServiceImpl implements AssistService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getOpenIdByToken(String token) {
        String openid = "";
        if(ObjectUtil.isStringNoBlank(token)){
            //通过token获取sessionkey
            String sessionKey = stringRedisTemplate.opsForValue().get(token);
            if(ObjectUtil.isStringNoBlank(sessionKey)){
                openid = stringRedisTemplate.opsForValue().get(sessionKey);
            }
        }
        return openid;
    }

    @Override
    public boolean checkToken(String token) {
        if(ObjectUtil.isStringNoBlank(token)){
            String tokenValue = stringRedisTemplate.opsForValue().get(token);
            if(ObjectUtil.isStringNoBlank(tokenValue)){
                return true;
            }
        }
        return false;
    }
}
