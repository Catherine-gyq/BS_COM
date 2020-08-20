package com.frankzhu.ems.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomMapper {
    @Select("select * from Room")
    List<Map<String, Object>> findAllRoom();

}
