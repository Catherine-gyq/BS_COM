package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
    @Select("select activity_id, A.resident_id as resident_id ,room_usage,name as resident_name,date,startTime,endTime,status from Room join Activity A join Resident R on " +
            "A.resident_id = R.resident_id and Room.room_id = A.room_id")
    List<Map<String,Object>> findallActivity();

    // 取消活动预约
    @Delete("delete from Activity where activity_id = #{activity_id}")
    Integer deleteActivity(@Param("activity_id") String activity_id);


}
