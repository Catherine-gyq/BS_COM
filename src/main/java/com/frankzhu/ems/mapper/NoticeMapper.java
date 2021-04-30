package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NoticeMapper {

    // 获取所有消息的数量
    @Select("select count(*) from Notice as N " +
            "where #{startTime} < N.publish_time and #{endTime} > N.publish_time and N.title like concat('%',#{name},'%')")
    Integer findAllNoticeTotalNum(@Param("name") String name, @Param("startTime") String startTime, @Param("endTime") String endTime);

    // 搜索所有消息(应该分类型的)
    @Select("select N.notice_id as id, N.publish_time as time, N.title as title, content, A.admin_name as people,A.admin_id as adminId, " +
            "A.admin_tele as tele from Notice as N join Admin as A on N.admin_id = A.admin_id " +
            "where #{startTime} < N.publish_time and #{endTime} > N.publish_time and N.title like concat('%',#{name},'%') " +
            "order by N.publish_time desc limit #{pageSize} OFFSET #{allNum} ")
    List<Map<String, Object>> findAllNoticeData(@Param("name") String name,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("allNum") int allNum,@Param("pageSize") int pageSize);

    // 添加社区消息(应该分类型的)
    @Insert("insert into Notice(title, publish_time, admin_id, content,type) VALUES" +
            "(#{title}, #{publish_time}, #{adminId}, #{content},'01')")
    Integer insertNotice(Notice notice);

    // 更新社区消息
    @Update("update Notice set title = #{notice.title}, content=#{notice.content}" +
            "where notice_id=#{id}")
    Integer updateNotice(Notice notice, @Param("id") int id);

    // 通过id删除消息
    @Delete("delete from Notice where notice_id=#{id}")
    Integer deleteNoticeById(@Param("id") String id);

}
