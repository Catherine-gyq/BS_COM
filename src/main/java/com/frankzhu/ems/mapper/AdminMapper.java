package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Admin;
import com.frankzhu.ems.model.Resident;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Repository
public interface AdminMapper {

    //获取管理员信息
    @Select("select admin_id,admin_name from Admin where admin_tele=#{tele}")
    List<Map<String, Object>> GetAdmin(@Param("tele") String tele);

    //管理员获取管理员的信息用于管理
    @Select("select * from Admin join Account A on A.usr_name = Admin.admin_tele where Admin.admin_tele like concat('%',#{tele},'%') " +
            " and Admin.admin_name like concat('%',#{name},'%') limit #{pageSize} OFFSET #{allNum}")
    List<Map<String, Object>> GetAdminInfo(@Param("tele") String tele,@Param("name") String name,@Param("pageSize") int pageSize,@Param("allNum") int allNum);

    // 查询管理员信息的条数
    @Select("select count(*) as totalNum from Admin where admin_tele like concat('%',#{tele},'%') and admin_tele like concat('%',#{name},'%')")
    Integer getAdminTotalNum(@Param("tele") String tele, @Param("name") String name);


    // 添加管理员
    @Insert("insert into Admin (admin_name, admin_sex, admin_tele, mailBox,dateOfBirth) VALUES" +
            "(#{name}, #{sex}, #{tele}, #{mailBox},#{dateOfBirth})")
    Integer insertAdmin(Admin admin);

    // 更新管理员信息
    @Update("update Admin set admin_name=#{resident.name}, admin_sex=#{resident.sex},"+
            " admin_tele=#{resident.tele},mailBox=#{mailBox}， dateOfBirth=#{resident.dateOfBirth}, where admin_id=#{id}")
    Integer updateAdmin(@Param("id") int id, Admin admin);

    // 删除管理员
    @Delete("delete from Admin where admin_tele=#{tele}")
    Integer deleteAdminByTele(@Param("tele") String tele);


}
