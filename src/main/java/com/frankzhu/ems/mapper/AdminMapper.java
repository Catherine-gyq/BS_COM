package com.frankzhu.ems.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Repository
public interface AdminMapper {

    //获取管理员信息
    @Select("select admin_id,admin_name from Admin where admin_tele=#{tele}")
    List<Map<String, Object>> GetAdmin(@Param("tele") String tele);

}
