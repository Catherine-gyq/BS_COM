package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityMapper {
    // 活动预约
    @Insert("insert into Activity (room_id, resident_id ,date,startTime, endTime, activity_usage,status) VALUES" +
            "(#{room_id}, #{resident_id}, #{date}, #{startTime}, #{endTime}, #{usage},#{status})")
    Integer reserveActivity(Activity activity);

    // 用户获取所有活动信息（分房间分页）
    @Select("select activity_id, A.resident_id as resident_id,activity_usage,admin_name,room_usage,name as resident_name,date,startTime,endTime," +
            "status from Room join Activity A on Room.room_id = A.room_id  join Resident R2 on A.resident_id = R2.resident_id " +
            "left join Admin A2 on A.admin_id = A2.admin_id where A.room_id =#{room_id} limit #{pageSize} OFFSET #{allNum}")
    List<Map<String,Object>> residentFindAllActivity(@Param("pageSize") int pageSize,@Param("allNum") int allNum,@Param("room_id") String room_id);

    // 取消活动预约
    @Delete("delete from Activity where activity_id = #{activity_id}")
    Integer deleteActivity(@Param("activity_id") String activity_id);

    //用户获取活动信息数量
    @Select("select count(*) from Activity where room_id = #{room_id}")
    Integer residentFindActivityNum(@Param("room_id") String room_id);

    // 管理员获取所有活动信息
    @Select("select activity_id, A.resident_id as resident_id,activity_usage,admin_name,room_usage,name as resident_name,date,startTime,endTime," +
            "status from Room join Activity A on Room.room_id = A.room_id  join Resident R2 on A.resident_id = R2.resident_id "+
            "left join Admin A2 on A.admin_id = A2.admin_id where A.room_id =#{room_id} and status=#{status} limit #{pageSize} OFFSET #{allNum}")
    List<Map<String,Object>> adminFindAllActivity(@Param("pageSize") int pageSize,@Param("allNum") int allNum,@Param("room_id") String room_id,@Param("status") String status);

    // 管理员获取活动信息的数量
    @Select("select count(*) from Activity where room_id = #{room_id} and status=#{status}")
    Integer adminFindActivityNum(@Param("room_id") String room_id,@Param("status") String status);

    //根据status获取
    @Select("select activity_id, A.resident_id as resident_id,activity_usage,admin_name,room_usage,name as resident_name,date,startTime,endTime," +
            "status from Room join Activity A on Room.room_id = A.room_id  join Resident R2 on A.resident_id = R2.resident_id " +
            "left join Admin A2 on A.admin_id = A2.admin_id where status=#{status} limit #{pageSize} OFFSET #{allNum}")
    List<Map<String,Object>> findAllActivityByStatus(@Param("pageSize") int pageSize,@Param("allNum") int allNum,@Param("status") String status);

    //根据status获取 数量
    @Select("select count(*) from Activity where status=#{status}")
    Integer findActivityNumByStatus(@Param("status") String status);

    //设置活动状态
    @Update("update Activity set status =#{status}, admin_id=#{adminId} where activity_id=#{activity_id}")
    Integer setActivityStatus(@Param("adminId") String adminId, @Param("activity_id") String repair_id,@Param("status") String status);

}
