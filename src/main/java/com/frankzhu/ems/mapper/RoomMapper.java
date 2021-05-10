package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomMapper {
    //获取所有的房间信息
    @Select("select * from Room")
    List<Map<String, Object>> findAllRoom();

    //获取所有的房间信息(分页)
    @Select("select * from Room")
    List<Map<String, Object>> findAllRoomByPage(@Param("allNum") Integer allNum,@Param("pageSize") Integer pageSize);

    //获取房间的数量
    @Select("select count(*) from Room")
    Integer findRoomNum();

    // 通过id删除房间信息
    @Delete("delete from Room where room_id=#{roomId}")
    Integer deleteRoomById(@Param("roomId") String roomId);

    // 通过id获取特定房间的信息
    @Select("select * from Room where room_id=#{roomId}")
    Map<String, Object> roomDetailById(@Param("roomId") Integer roomId);

    //添加房间信息
    @Insert("insert into AdviseBox(dateTime, resident_id, title, content, status) VALUES"+
            "(#{dateTime}, #{resident_id}, #{title}, #{content}, #{status})")
    Integer insertRoom(Room room);

    //添加房间信息
    @Insert("insert into AdviseBox(dateTime, resident_id, title, content, status) VALUES"+
            "(#{dateTime}, #{resident_id}, #{title}, #{content}, #{status})")
    Integer updateRoom(Room room);

}
