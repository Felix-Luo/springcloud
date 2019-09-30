package com.msg.service.impl;

import com.msg.mapper.TcommentMapper;
import com.msg.pojo.Tcomment;
import com.msg.service.TcommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description:    TcommentServiceImpl接口
* @Author:         luoyhong
* @CreateDate:     2019/6/26 18:45
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/26 18:45
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Transactional
@Service
public class TcommentServiceImpl implements TcommentService {

    private final static Logger logger = LoggerFactory.getLogger(TcommentServiceImpl.class);
    @Autowired
    TcommentMapper tcommentMapper;

    @Override
    public boolean add(Tcomment tcomment) {
        try{
            tcommentMapper.add(tcomment);
            return true;
        }catch (Exception e){
            logger.error("添加评论信息异常",e);
            return false;
        }
    }

    @Override
    public boolean delete(Tcomment tcomment) {
        try{
            tcommentMapper.delete(tcomment);
            return true;
        }catch (Exception e){
            logger.error("删除评论信息异常",e);
            return false;
        }
    }

    @Override
    public List<Map> query(Map param) {
        List<Map> tcommentList =  new ArrayList<>();
        try{
            tcommentList = tcommentMapper.query(param);
            return tcommentList;
        }catch (Exception e){
            logger.error("查询评论信息异常",e);
            return tcommentList;
        }
    }

    @Override
    public boolean update(Tcomment tcomment) {
        try{
            tcommentMapper.update(tcomment);
            return true;
        }catch (Exception e){
            logger.error("更新评论信息异常",e);
            return false;
        }
    }
}
