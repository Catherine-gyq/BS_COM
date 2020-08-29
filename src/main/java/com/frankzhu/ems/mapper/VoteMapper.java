package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Notice;
import com.frankzhu.ems.model.Vote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VoteMapper {

    // 添加投票信息
    @Insert("select N.notice_id as id, N.publish_time as time, N.title as title, content, A.admin_name as people,A.admin_id as admin_id,"
            +"A.admin_tele as tele from Notice as N join Admin as A on N.admin_id = A.admin_id where type='02'")
    Integer insertVote(Vote vote);

    // 统计投票信息
    @Select("select is_agree,count(is_agree) as num from VoteDetails where vote_id = #{vote_id} group by is_agree;")
    List<Map<String, Object> >voteCount(@Param("vote_id") String vote_id);

    // 搜索所有消息
    @Select("select N.notice_id as id, N.publish_time as time, N.title as title, content, A.admin_name as people,A.admin_id as admin_id,\n" +
            "A.admin_tele as tele from Notice as N join Admin as A on N.admin_id = A.admin_id where type='02'")
    List<Map<String, Object>> findAllVote();


}
