package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NoticeMapper {

    // 搜索课程,按课程名、课程号模糊查询
    @Select("select N.notice_id as id, N.publish_time as time, N.title as title, content, A.admin_name as people,A.admin_id as admin_id, " +
            "A.admin_tele as tele from Notice as N join Admin as A on N.admin_id = A.admin_id " +
            "where N.publish_time like concat('%',#{time},'%') and N.title like concat('%',#{name},'%')")
    List<Map<String, Object>> findAllNotice(@Param("time") String time, @Param("name") String name);

    // 添加社区消息
    @Insert("insert into Notice(title, publish_time, admin_id, content,type) VALUES" +
            "(#{notice.title}, #{notice.publish_time}, #{id}, #{notice.content},'01')")
    Integer insertNotice(Notice notice, @Param("id") String id);

    // 更新社区消息
    @Update("update Notice set title = #{notice.title}, content=#{notice.content}" +
            "where notice_id=#{id}")
    Integer updateNotice(Notice notice, @Param("id") String id);

    // 通过id删除消息
    @Delete("delete from Notice where notice_id=#{id}")
    Integer deleteNoticeById(@Param("id") String id);

}
