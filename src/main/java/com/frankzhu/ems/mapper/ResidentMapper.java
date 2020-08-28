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
    // 模糊查询居民
    @Select("select * from Resident where tele like concat('%',#{usr_tele},'%') and name like concat('%',#{name},'%')")
    List<Map<String, Object>> findAllResident(@Param("usr_tele") String usr_tele, @Param("name") String name);

//   添加居民
    @Insert("insert into Resident (name, sex, tele, address) VALUES" +
            "(#{name}, #{sex}, #{tele}, #{address})")
    Integer insertResident(Resident resident);

//    // 更新信息
    @Update("update Resident set name=#{resident.name}, sex=#{resident.sex},"+
            " tele=#{resident.tele}, address=#{resident.address} where resident_id=#{id}")
    Integer updateResident(@Param("id") String id, Resident resident);

    // 删除居民
    @Delete("delete from Resident where tele=#{tele}")
    Integer deleteResidentByTele(@Param("tele") String tele);

    // 查询当前居民
    @Select("select resident_id,name from Resident where tele=#{tele}")
    Map<String, Object> GetResident(@Param("tele") String tele);

}
