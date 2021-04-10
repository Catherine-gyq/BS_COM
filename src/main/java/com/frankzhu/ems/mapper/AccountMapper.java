package com.frankzhu.ems.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AccountMapper {

    // 搜索是否存在该用户
    @Select("select * from Account where usr_name=#{no} and type= #{ident}")
    Map<String, Object> findAccount(@Param("no") String no, @Param("ident") String ident);


    // 搜索用户密码是否匹配
    @Select("select * from Account where usr_name=#{no} and password=#{pwd} and type= #{ident}")
    Map<String, Object> findAccountPass(@Param("no") String no, @Param("pwd") String pwd, @Param("ident") String ident);

    // 重置密码
    @Update("update Account set password=#{pwd} where usr_name=#{name} and type=#{ident}")
    Integer resetPassword(@Param("name") String name, @Param("pwd") String pwd, @Param("ident") String ident);

//    获取管理员的所有信息
    @Select("select * from Account join Admin A on Account.usr_name = A.admin_tele where usr_name=#{username} and type= #{identity}")
    Map<String, Object> getAdminInformation(@Param("username") String username, @Param("identity") String identity);

//    获取用户的所有信息
    @Select("select * from Account join Resident R on Account.usr_name = R.tele where usr_name=#{username} and type= #{identity}")
    Map<String, Object> getResidentInformation(@Param("username") String username, @Param("identity") String identity);






    // 密码修改
//    @Update("update Account set password=#{pwd} where usr_name=#{name} and type=#{ident}")
//    Integer updateAccount(@Param("name") String name, @Param("newPassword") String newPassword, @Param("ident") String ident);


//    // 删除账号
//    @Delete("delete from Account where usr_name=#{tele}")
//    Integer deleteAccountByTele(@Param("tele") String tele);


    //重置手机号
//    @Update("update account set password=#{pwd} where no=#{no} and identity=#{ident}")
//    Integer updateAccounttele(@Param("tele") String tele);


    // 新增账号
    @Insert("insert into Account(usr_name, password, type) VALUES (#{tele}, #{pwd}, #{type})")
    Integer addAccount(@Param("tele") String tele, @Param("pwd") String pwd, @Param("type") String type);

}
