package com.msg.service.impl;


import com.msg.mapper.TusedMapper;
import com.msg.pojo.Tused;
import com.msg.service.TusedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description:    TusedServiceImpl接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 17:43
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 17:43
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Transactional
@Service
public class TusedServiceImpl implements TusedService{

    private final static Logger logger = LoggerFactory.getLogger(TusedServiceImpl.class);
    @Autowired
    TusedMapper tusedMapper;

    @Override
    public boolean add(Tused tused) {
        try{
            tusedMapper.add(tused);
            return true;
        }catch (Exception e){
            logger.error("添加设备使用记录异常",e);
            return false;
        }
    }

    @Override
    public List<Tused> query(Map param) {
        List<Tused> tusedList =  new ArrayList<>();
        try{
            tusedList = tusedMapper.query(param);
        }catch (Exception e){
            logger.error("查询设备使用记录异常",e);
        }
        return tusedList;
    }

    @Override
    public List<Map> countByTime(Map param) {
        List<Map> mapList =  new ArrayList<>();
        try{
            mapList = tusedMapper.countByTime(param);
        }catch (Exception e){
            logger.error("获取某一段时间内的使用次数异常",e);
        }
        return mapList;
    }
}
