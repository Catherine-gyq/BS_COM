package com.frankzhu.ems.mapper;

import com.frankzhu.ems.model.Repair;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepairMapper {

    // student grade
//    @Select("select c.no as cid, c.name as cname, c.credit, t.name as tname ,e.grade from open as o join teacher as t " +
//            "join term as te join course as c join elective as e on o.course=c.no and o.teacher=t.no " +
//            "and o.term=te.no and e.open=o.id where e.student=#{student} and te.no=#{term}")
//    List<Map<String, Object>> findGradeByStudentNo(@Param("no") String no, @Param("term") String term);

    // search students
//    @Select("select o.id, e.id as eid, s.no, s.name as name, s.sex, d.name as department, e.grade from" +
//            " elective as e join open as o join student as s join department as d on e.open=o.id " +
//            "and e.student=s.no and s.department=d.no where o.id=#{oid}")
//    List<Map<String, Object>> findStudentByCourseNo(@Param("oid") String oid);

    // search enable courses
//    @Select("select o.id, c.no as cid, c.name as cname, c.credit, t.name as tname, o.time, o.studentNumber " +
//            "from open as o join teacher as t join term as te join course as c on o.course=c.no " +
//            "and o.teacher=t.no and o.term=te.no where o.id not in (select open from elective where student=#{no}) " +
//            "and te.no=(select max(no) from term) and t.no like concat('%',#{tno},'%') and t.name like concat('%',#{tname},'%') " +
//            "and c.no like concat('%',#{cno},'%') and c.name like concat('%',#{cname},'%') and o.choice is true")
//    List<Map<String, Object>> findEnableCourseByStudentNo(
//            @Param("no") String no,
//            @Param("tno") String tno,
//            @Param("tname") String tname,
//            @Param("cno") String cno,
//            @Param("cname") String cname
//    );

    // search own courses
//    @Select("select o.id, c.no as cid, c.name as cname, c.credit, t.name as tname, o.time, o.studentNumber " +
//            "from open as o join teacher as t join term as te join course as c on o.course=c.no " +
//            "and o.teacher=t.no and o.term=te.no where o.id in " +
//            "(select open from elective where student=#{no} and grade is null)")
//    List<Map<String, Object>> findOwnCourseByStudentNo(@Param("no") String no);

    // 添加维修预约消息
    @Insert("insert into Repair(repair_time, resident_id, repair_address, repair_content, repair_status) VALUES "+
    "(#{repair_time},#{resident_id},#{repair_address},#{repair_content},#{status})")
    Integer insertRepair(Repair repair);

    // 获取维修预约的所有消息
    @Select("select repair_id,repair_time as date,repair_address as address,repair_content as "+
            "content,repair_status as status from Repair where resident_id=#{id}")
    List<Map<String, Object> >findAllRepair(@Param("id") String id);

    //  取消维修预约
    @Delete("delete from Repair where repair_id = #{repair_id}")
    Integer deleteRepair(@Param("repair_id") String repair_id);



    // update
//    @Update("update elective set grade=#{grade} where id=#{id}")
//    Integer updateElective(Repair repair);

    // delete
//    @Delete("delete from elective where student=#{sno} and open=#{cno}")
//    Integer deleteElectiveByNo(@Param("sno") String sno, @Param("cno") String cno);

}
