package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RepairMapper;
import com.frankzhu.ems.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RepairController {

    private final RepairMapper repairMapper;

    @Autowired
    public RepairController(RepairMapper repairMapper){
        this.repairMapper = repairMapper;
    }

//    @GetMapping("/api/elective/find/grade/student")
//    public List<Map<String, Object>> findGradeByStudentNo(
//            @RequestParam(value = "no", defaultValue = "")String no,
//            @RequestParam(value = "term", defaultValue = "")String term){
//        return electiveMapper.findGradeByStudentNo(no, term);
//    }

//    @GetMapping("/api/elective/find/student/course")
//    public List<Map<String, Object>> findStudentByOID(@RequestParam(value = "no", defaultValue = "")String oid){
//        return repairMapper.findStudentByCourseNo(oid);
//    }

//    @PostMapping("/api/elective/find/enableCourse/student")
//    public List<Map<String, Object>> findEnableCourseByStudentNo(@RequestBody Map<String, Object> params){
//        String no = params.get("no").toString();
//        String tno = params.get("tno").toString();
//        String tname = params.get("tname").toString();
//        String cno = params.get("cno").toString();
//        String cname = params.get("cname").toString();
//        return repairMapper.findEnableCourseByStudentNo(no, tno, tname, cno, cname);
//    }

//    @GetMapping("/api/elective/find/OwnCourse/student")
//    public List<Map<String, Object>> findOwnCourseByStudentNo(@RequestParam(value = "no", defaultValue = "") String no){
//        return repairMapper.findOwnCourseByStudentNo(no);
//    }
    //获取所有预约信息
    @GetMapping("/api/repair/all")
    public List<Map<String, Object>> findAllRepair(@RequestParam(value = "id", defaultValue = "") String id){
        return repairMapper.findAllRepair(id);
    }

    // 增加预约
    @PostMapping("/api/repair/add")
    public Integer insertRepair(@RequestBody Map<String, Object> params){
        String repair_time = params.get("date").toString();
        String resident_id = params.get("resident_id").toString();
        String repair_address = params.get("address").toString();
        String repair_content = params.get("content").toString();
        String status = "待处理";
        return repairMapper.insertRepair(new Repair(repair_time,resident_id,repair_address,repair_content,status));
    }

    //  取消预约
    @GetMapping("/api/repair/cancel")
    public Integer deleteActivity(@RequestParam(value ="repair_id", defaultValue = "") String repair_id){
        System.out.println("活动编号"+repair_id);
        return repairMapper.deleteRepair(repair_id);
    }



//    @PostMapping("/api/elective/update")
//    public Integer updateElective(@RequestBody Map<String, Object> params){
//        Integer id = Integer.parseInt(params.get("id").toString());
//        Integer grade = Integer.parseInt(params.get("grade").toString());
//        return repairMapper.updateElective(new Repair(id, grade, null, null));
//    }

//    @GetMapping("/api/elective/delete")
//    public Integer deleteElectiveByNo(
//            @RequestParam(value = "sno", defaultValue = "") String sno,
//            @RequestParam(value = "cno", defaultValue = "") String cno){
//        return repairMapper.deleteElectiveByNo(sno, cno);
//    }

}
