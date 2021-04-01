package com.frankzhu.ems.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AccountMapper {

    // 搜索用户密码是否匹配
    @Select("select * from Account where usr_name=#{no} and password=#{pwd} and type= #{ident}")
    Map<String, Object> findAccount(@Param("no") String no, @Param("pwd") String pwd, @Param("ident") String ident);

    // 重置密码
    @Update("update Account set password=#{pwd} where usr_name=#{no} and type=#{ident}")
    Integer updateAccount(@Param("no") String no, @Param("pwd") String pwd, @Param("ident") String ident);


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
