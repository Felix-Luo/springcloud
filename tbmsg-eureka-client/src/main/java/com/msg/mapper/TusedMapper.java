package com.msg.mapper;

import com.msg.pojo.Tused;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TusedMapper {
    boolean add(Tused tused);
    List<Tused> query(Map param);
    List<Map> countByTime(Map param);
}
