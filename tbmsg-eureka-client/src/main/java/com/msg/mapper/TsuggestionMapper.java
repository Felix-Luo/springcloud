package com.msg.mapper;

import com.msg.pojo.Tsuggestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TsuggestionMapper {
    public boolean add(Tsuggestion tsuggestion);
    public List<Tsuggestion> query(Map param);
}
