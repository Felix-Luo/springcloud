package com.msg.mapper;

import com.msg.pojo.Tuser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TuserMapper {
    boolean add(Tuser tuser);
    int delete(Tuser tuser);
    List<Tuser> query(Map param);
    boolean update(Tuser tuser);
}
