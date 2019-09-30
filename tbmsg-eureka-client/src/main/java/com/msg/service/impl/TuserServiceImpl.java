package com.msg.service.impl;

import com.msg.mapper.TuserMapper;
import com.msg.pojo.Tuser;
import com.msg.service.TuserService;
import com.msg.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description:    TuserServiceImpl接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 13:51
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 13:51
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Transactional
@Service
public class TuserServiceImpl implements TuserService{

    private final static Logger logger = LoggerFactory.getLogger(TuserServiceImpl.class);

    @Autowired
    TuserMapper tuserMapper;

    @Override
    public boolean add(Tuser tuser) {
        try{
            tuserMapper.add(tuser);
            return true;
        }catch (Exception e){
            logger.error("添加用户信息异常",e);
            return false;
        }
    }

    @Override
    public int delete(Tuser tuser) {
        int count = 0;
        try{
            if(tuser.getId()==null){
                /*如果id为空，并且appid与openid有一个为空则返回，停止往下执行*/
                if(!ObjectUtil.isStringNoBlank(tuser.getAppid())||!ObjectUtil.isStringNoBlank(tuser.getOpenid())){
                    return count;
                }
            }
            count = tuserMapper.delete(tuser);
        }catch (Exception e){
            logger.error("删除用户信息异常",e);
        }
        return count;
    }

    @Override
    public List<Tuser> query(Map param) {
        List<Tuser> tuserList =  new ArrayList<>();
        try{
            tuserList = tuserMapper.query(param);
            return tuserList;
        }catch (Exception e){
            logger.error("查询用户信息异常",e);
            return tuserList;
        }
    }

    @Override
    public boolean update(Tuser tuser) {
        try{
            tuserMapper.update(tuser);
            return true;
        }catch (Exception e){
            logger.error("更新用户信息异常",e);
            return false;
        }
    }
}
