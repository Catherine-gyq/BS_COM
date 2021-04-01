package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RepairMapper;
import com.frankzhu.ems.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    //用户获取所有维修消息
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
        int status = 0;
        return repairMapper.insertRepair(new Repair(repair_time,resident_id,repair_address,repair_content,status));
    }

    //  取消预约
    @GetMapping("/api/repair/cancel")
    public Integer deleteRepair(@RequestParam(value ="repair_id", defaultValue = "") String repair_id){
        System.out.println("预约编号"+repair_id);
        return repairMapper.deleteRepair(repair_id);
    }

    //  从管理员视角获取所有维修预约
    @PostMapping("api/repair/allInfo")
    public Map<String, Object> getAllRepairs(@RequestBody Map<String, Object> params){
        String startTime = params.get("startTime").toString();
        String endTime = params.get("endTime").toString();
        String status = params.get("reserveStatus").toString();
        if (startTime.length()==0){
            startTime = "0000-01-01";
        }
        if (endTime.length()==0){
            endTime = "9999-01-01";
        }
        String pageSize1 = params.get("pageSize").toString();
        String currentPage1 = params.get("currentPage").toString();

        pageSize1 = pageSize1.substring(0,pageSize1.length()-2);
        currentPage1 = currentPage1.substring(0,currentPage1.length()-2);

        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        int totalNum =repairMapper.findAllRepairTotalNum(startTime,endTime,status);
        List<Map<String, Object>> noticeInformation  = repairMapper.findAllRepairData(startTime,endTime,status,allNum,pageSize);
        Map<String, Object> repairs =new HashMap<String, Object>();
        repairs.put("totalNum", totalNum);
        repairs.put("residentInfo",noticeInformation);
        return repairs;
//        return repairMapper.findAllRepairs(startTime,endTime,status);
    }

    //管理员将维修预约同意（暂时没有时间）
    @GetMapping("/api/repair/approve")
    public Integer handleRepair(@RequestParam(value ="repair_id", defaultValue = "") String repair_id){
        System.out.println("预约编号"+repair_id);
        return repairMapper.approveRepair(repair_id);
    }


}
