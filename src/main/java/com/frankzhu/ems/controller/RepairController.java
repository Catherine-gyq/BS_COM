package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RepairMapper;
import com.frankzhu.ems.model.Repair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "维修管理")
@RestController
public class RepairController {

    private final RepairMapper repairMapper;

    @Autowired
    public RepairController(RepairMapper repairMapper){
        this.repairMapper = repairMapper;
    }

    //用户获取所有维修消息
    @GetMapping("/api/repair/all")
    @ApiOperation("用户获取所有维修消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数量",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "int"),
    })
    public Map<String, Object> findAllRepair(
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage){
//        修改
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> repairInformation = repairMapper.findAllRepair(id,pageSize,allNum);
        int totalNum =  repairMapper.findRepairTotalNum(id);
        Map<String, Object> resident =new HashMap<String, Object>();
        resident.put("totalNum", totalNum);
        resident.put("repairInfo",repairInformation);
        return resident;
    }
    // 增加预约
    @PostMapping("/api/repair/add")
    @ApiOperation("用户增加预约维修")
    public Integer insertRepair(@RequestBody Repair repair){
        int status = repair.getStatus();
        System.out.println(status);
        return repairMapper.insertRepair(repair);
    }

    //  取消预约
    @GetMapping("/api/repair/cancel")
    @ApiOperation("用户取消维修预约")
    @ApiImplicitParam(name = "repair_id",value = "预约id",required = true,paramType = "query",dataType = "String")
    public Integer deleteRepair(@RequestParam(value ="repair_id", defaultValue = "") String repair_id){
//        System.out.println("预约编号"+repair_id);
        return repairMapper.deleteRepair(repair_id);
    }

    //  从管理员视角获取所有维修预约
    @PostMapping("api/repair/allInfo")
    @ApiOperation("管理员获取所有的维修预约")
    public Map<String, Object> getAllRepairs(@RequestBody Map<String, Object> params){
        String status = params.get("reserveStatus").toString();
        String startTime;
        String endTime;
        if (params.containsKey("startTime") && params.get("startTime").toString().length()!=0){
            startTime = params.get("startTime").toString();
        }else{
            startTime = "0000-01-01";
        }
        if (params.containsKey("endTime") && params.get("endTime").toString().length()!=0){
            endTime = params.get("endTime").toString();
        }else{
            endTime = "9999-01-01";
        }
        String pageSize1 = params.get("pageSize").toString();
        String currentPage1 = params.get("currentPage").toString();
//        pageSize1 = pageSize1.substring(0,pageSize1.length()-2);
//        currentPage1 = currentPage1.substring(0,currentPage1.length()-2);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        int totalNum =repairMapper.findAllRepairTotalNum(startTime,endTime,status);
        List<Map<String, Object>> noticeInformation  = repairMapper.findAllRepairData(startTime,endTime,status,allNum,pageSize);
        Map<String, Object> repairs =new HashMap<String, Object>();
        repairs.put("totalNum", totalNum);
        repairs.put("repairInfo",noticeInformation);
        return repairs;
    }

    //管理员将维修预约同意（暂时没有时间）
    @GetMapping("/api/repair/approve")
    @ApiOperation("用户获取所有维修消息")
    @ApiImplicitParam(name = "repair_id",value = "维修id",required = true,paramType = "query",dataType = "String")
    public Integer handleRepair(@RequestParam(value ="repair_id", defaultValue = "") String repair_id){
        System.out.println("预约编号"+repair_id);
        return repairMapper.approveRepair(repair_id);
    }
}
