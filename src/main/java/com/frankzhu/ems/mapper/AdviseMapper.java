package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Advise;
import com.frankzhu.ems.model.Vote;
import org.aopalliance.aop.Advice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdviseMapper {

    //获取意见箱中所有建议
    @Select("select advise_id,box_time as date,box_title as title,box_content as content,box_status as status,name as residentName from AdviseBox" +
            " join Resident R on R.resident_id = AdviseBox.resident_id where box_status=#{status} limit #{pageSize} offset #{allNum} ")
    List<Map<String, Object>>findAllAdvise(@Param("allNum") int allNum,@Param("pageSize") int pageSize,@Param("status") String status);

    // 获取意见箱中的建议数量
    @Select("select count(*) from AdviseBox where box_status=#{status}")
    Integer findAdviseNum(@Param("status") String status);

    //添加建议
    @Insert("insert into AdviseBox(box_time, resident_id, box_title, box_content, box_status) VALUES"+
            "(#{datetime}, #{resident_id}, #{box_title}, #{box_content}, #{status})")
    Integer insertAdvice(Advise advise);

    //改变建议的状态
    @Insert("update AdviseBox set box_status=#{status} where advise_id=#{advise_id}")
    Integer changeStatus(@Param("advise_id") int advise_id,@Param("status") String status);

}
