package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityMapper {

    // 活动预约
    @Insert("insert into Activity (room_id, resident_id ,date,startTime, endTime, activity_usage,status) VALUES" +
            "(#{room_id}, #{resident_id}, #{date}, #{startTime}, #{endTime}, #{usage},#{status})")
    Integer reserveActivity(Activity activity);

    // 获取所有活动信息
    @Select("")
    List<Map<String,Object>> findallActivity();
}
