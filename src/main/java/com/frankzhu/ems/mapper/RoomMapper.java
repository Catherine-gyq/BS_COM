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

    // 通过id删除房间信息
    @Delete("delete from Room where room_id=#{roomId}")
    Integer deleteRoomById(@Param("roomId") String roomId);

    //添加房间信息
    @Insert("insert into AdviseBox(dateTime, resident_id, title, content, status) VALUES"+
            "(#{dateTime}, #{resident_id}, #{title}, #{content}, #{status})")
    Integer insertRoom(Room room);

    //添加房间信息
    @Insert("insert into AdviseBox(dateTime, resident_id, title, content, status) VALUES"+
            "(#{dateTime}, #{resident_id}, #{title}, #{content}, #{status})")
    Integer updateRoom(Room room);

}
