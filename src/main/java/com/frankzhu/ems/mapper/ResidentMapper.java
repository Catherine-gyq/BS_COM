package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Resident;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResidentMapper {
//    @Select("select s.no, s.name as name, s.sex, s.birthday, d.name as department, d.no as departmentID " +
//            "from Resident as R join department as d on s.department=d.no " +
//            "where s.no like concat('%',#{no},'%') and s.name like concat('%',#{name},'%')")
    // 模糊查询居民信息
    @Select("select * from Resident where tele like concat('%',#{usr_tele},'%') and name like concat('%',#{name},'%') limit #{pageSize} OFFSET #{allNum}")
    List<Map<String, Object>> findAllResidentData(@Param("usr_tele") String usr_tele, @Param("name") String name,@Param("allNum") Integer allNum, @Param("pageSize") Integer pageSize);

    // 查询居民信息的条数
    @Select("select count(*) as totalNum from Resident where tele like concat('%',#{usr_tele},'%') and name like concat('%',#{name},'%')")
    Integer findResidentTotalNum(@Param("usr_tele") String usr_tele, @Param("name") String name);


    // 添加居民
    @Insert("insert into Resident (name, sex, tele, address,mailbox) VALUES" +
            "(#{name}, #{sex}, #{tele}, #{address}, #{mailBox})")
    Integer insertResident(Resident resident);

    // 更新居民信息
    @Update("update Resident set name=#{resident.name}, sex=#{resident.sex},"+
            " tele=#{resident.tele}, address=#{resident.address}, mailBox=#{resident.mailBox} where resident_id=#{id}")
    Integer updateResident(@Param("id") int id, Resident resident);

    // 删除居民
    @Delete("delete from Resident where tele=#{tele}")
    Integer deleteResidentByTele(@Param("tele") String tele);

    // 查询当前居民
    @Select("select * from Resident where tele=#{tele}")
    Map<String, Object> GetResident(@Param("tele") String tele);

}
