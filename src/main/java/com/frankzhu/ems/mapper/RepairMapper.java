package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Repair;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepairMapper {

    // 添加维修预约消息
    @Insert("insert into Repair(repair_time, resident_id, repair_address, repair_content, repair_status) VALUES "+
    "(#{repair_time},#{resident_id},#{repair_address},#{repair_content},#{status})")
    Integer insertRepair(Repair repair);

    // 用户获取维修预约的所有消息
    @Select("select repair_id,repair_time as date,repair_address as address,repair_content as "+
            "content,repair_status as status from Repair where resident_id=#{id} limit #{pageSize} offset #{allNum}")
    List<Map<String, Object> >findAllRepair(@Param("id") String id,@Param("pageSize") int pageSize,@Param("allNum") int allNum);

    // 用户获取维修预约的消息数量

    @Select("select count(*) from Repair where resident_id=#{id}")
    Integer findRepairTotalNum(@Param("id") String id);

    // 管理员获取维修预约的所有消息
    @Select("select repair_id,repair_time as date,repair_address as address,repair_content as " +
            "content,repair_status as status,admin_name as adminName,name as residentName " +
            "from Repair as R join Resident R2 on R2.resident_id = R.resident_id " +
            "left join Admin A on A.admin_id = R.admin_id where #{startTime}<repair_time and #{endTime}>repair_time and repair_status = #{status} limit #{pageSize} OFFSET #{allNum}"
    )
    List<Map<String, Object> >findAllRepairData(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("status") String status,@Param("allNum") int allNum,@Param("pageSize") int pageSize);

    // 管理员获取维修预约的消息数量
    @Select("select count(*) from Repair as R  where #{startTime}< repair_time and #{endTime}>repair_time and repair_status =#{status}")
    Integer findAllRepairTotalNum(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("status") String status);



    //  取消维修预约
    @Delete("delete from Repair where repair_id = #{repair_id}")
    Integer deleteRepair(@Param("repair_id") String repair_id);

    // 管理员同意预约内容
    @Select("update Repair set repair_status = 1 where repair_id=#{id}")
    Integer approveRepair(@Param("id") String id);
    // update
//    @Update("update elective set grade=#{grade} where id=#{id}")
//    Integer updateElective(Repair repair);

    // delete
//    @Delete("delete from elective where student=#{sno} and open=#{cno}")
//    Integer deleteElectiveByNo(@Param("sno") String sno, @Param("cno") String cno);

}
