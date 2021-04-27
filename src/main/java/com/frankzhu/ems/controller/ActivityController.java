package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.ActivityMapper;
import com.frankzhu.ems.model.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "活动预约管理")
@RestController
public class ActivityController {

    private final ActivityMapper activityMapper;

    public ActivityController(ActivityMapper activityMapper){
        this.activityMapper = activityMapper;
    }

    @PostMapping("api/activity/reserve")
    @ApiOperation("住户进行活动预约")
    public Integer ReserveActivity(@RequestBody Map<String,Object> params){
        String status = "待处理";
        String date = params.get("date").toString();
        String usage = params.get("usage").toString();
        String resident_id = params.get("resident_id").toString();
        String startTime = params.get("startTime").toString();
        String endTime = params.get("endTime").toString();
        String room_id = params.get("room_id").toString();
        return activityMapper.reserveActivity(new Activity(room_id,resident_id,usage,date,startTime,endTime,status));
    }


    @GetMapping("/api/activity/cancel")
    @ApiOperation("取消活动预约")
    @ApiImplicitParam(name="activity_id",value = "活动id",required = true,paramType = "query",dataType = "String")
    public Integer deleteActivity(@RequestParam(value ="activity_id", defaultValue = "") String activity_id){
        return activityMapper.deleteActivity(activity_id);
    }

   //住户获取所有活动预约（根据room分）
    @GetMapping("api/activity/allInfo")
    @ApiOperation("住户获取所有活动预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "room_id",value = "房间号",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "String")
    })
    public Map<String, Object> residentFindAllActivity(
            @RequestParam(value = "room_id", defaultValue = "") String room_id,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage){
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> activityInformation = activityMapper.residentFindAllActivity(pageSize,allNum,room_id);
        int totalNum = activityMapper.residentFindActivityNum(room_id);
        Map<String, Object> activity =new HashMap<String, Object>();
        activity.put("totalNum", totalNum);
        activity.put("activityInfo",activityInformation);
        return activity;
    }

    //管理员获取所有活动预约（根据room/status分）
    @GetMapping("api/activity/all")
    @ApiOperation("住户获取所有活动预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "room_id",value = "房间号",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "审批状态",required = true,paramType = "query",dataType = "String")
    })
    public Map<String, Object> adminFindAllActivity(
            @RequestParam(value = "room_id", defaultValue = "") String room_id,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage,
            @RequestParam(value = "status", defaultValue = "") String status){
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> activityInformation = new ArrayList<>();
        Integer totalNum = 0;
        System.out.println(room_id);
        if (room_id.isEmpty()){
            activityInformation = activityMapper.findAllActivityByStatus(pageSize,allNum,status);
            totalNum = activityMapper.findActivityNumByStatus(status);
        }else{
            activityInformation = activityMapper.adminFindAllActivity(pageSize,allNum,room_id,status);
            totalNum = activityMapper.adminFindActivityNum(room_id,status);
        }
        Map<String, Object> activity =new HashMap<String, Object>();
        activity.put("totalNum", totalNum);
        activity.put("activityInfo",activityInformation);
        return activity;
    }

    //管理员设置活动预约的状态
    @GetMapping("api/activity/approve")
    @ApiOperation("设置活动预约状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId",value = "管理员id",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "activity_id",value = "维修id",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "status",value = "审批状态",required = true,paramType = "query",dataType = "String")
    })
    public Integer setActivity(
            @RequestParam(value = "adminId", defaultValue = "") String adminId,
            @RequestParam(value = "activity_id", defaultValue = "") String activity_id,
            @RequestParam(value = "status", defaultValue = "") String status){
        System.out.println("设置预约状态");
        System.out.println(adminId);
        System.out.println(activity_id);
        System.out.println(status);
        return activityMapper.setActivityStatus(adminId,activity_id,status);
    }

}
