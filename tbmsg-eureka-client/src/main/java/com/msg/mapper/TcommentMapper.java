package com.msg.mapper;

import com.msg.pojo.Tcomment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TcommentMapper {
    boolean add(Tcomment tcomment);
    boolean delete(Tcomment tcomment);
    List<Map> query(Map param);
    boolean update(Tcomment tcomment);
}
