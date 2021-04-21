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

    //管理员获取意见箱中所有建议
    @Select("select advise_id,dateTime as date,title,content,status,name as residentName from AdviseBox" +
            " join Resident R on R.resident_id = AdviseBox.resident_id where status=#{status} limit #{pageSize} offset #{allNum} ")
    List<Map<String, Object>>adminFindAllAdvise(@Param("allNum") int allNum,@Param("pageSize") int pageSize,@Param("status") String status);

    //管理员获取意见箱中的建议数量
    @Select("select count(*) from AdviseBox where status=#{status}")
    Integer adminFindAdviseNum(@Param("status") String status);

    //用户获取意见箱中所有意见
    @Select("select advise_id,dateTime as date,title,content,status,name as residentName from AdviseBox" +
            " join Resident R on R.resident_id = AdviseBox.resident_id where AdviseBox.resident_id=#{resident_id} limit #{pageSize} offset #{allNum}")
    List<Map<String, Object>>residentFindAllAdvise(@Param("allNum") int allNum,@Param("pageSize") int pageSize,@Param("resident_id") String resident_id);


    //用户获取意见箱中所有意见
    @Select("select count(*) from AdviseBox where resident_id=#{resident_id}")
    Integer residentFindAdviseNum(@Param("resident_id") String resident_id);

    //添加建议
    @Insert("insert into AdviseBox(dateTime, resident_id, title, content, status) VALUES"+
            "(#{dateTime}, #{resident_id}, #{title}, #{content}, #{status})")
    Integer insertAdvice(Advise advise);

    //改变建议的状态
    @Insert("update AdviseBox set status=#{status} where advise_id=#{advise_id}")
    Integer changeStatus(@Param("advise_id") int advise_id,@Param("status") String status);

}
