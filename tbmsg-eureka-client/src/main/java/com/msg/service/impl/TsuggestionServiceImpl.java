package com.msg.service.impl;


import com.msg.mapper.TsuggestionMapper;
import com.msg.pojo.Tsuggestion;
import com.msg.service.TsuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description:    TsuggestionServiceImpl接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:32
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:32
* @UpdateRemark:   修改内容
* @Version:        1.0
*/

/**
* @Description:    TsuggestionServiceImpl接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:45
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:45
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Transactional
@Service
public class TsuggestionServiceImpl implements TsuggestionService{
    private final static Logger logger = LoggerFactory.getLogger(TsuggestionServiceImpl.class);
    @Autowired
    TsuggestionMapper tsuggestionMapper;

    @Override
    public boolean add(Tsuggestion tsuggestion) {
        try{
            tsuggestionMapper.add(tsuggestion);
            return true;
        }catch (Exception e){
            logger.error("添加意见反馈异常",e);
            return false;
        }
    }

    @Override
    public List<Tsuggestion> query(Map param) {
        List<Tsuggestion> tsuggestionList =  new ArrayList<>();
        try{
            tsuggestionList = tsuggestionMapper.query(param);
            return tsuggestionList;
        }catch (Exception e){
            logger.error("查询意见反馈异常",e);
            return tsuggestionList;
        }
    }
}
