package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.ActivityMapper;
import com.frankzhu.ems.model.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "活动预约管理")
@RestController
public class ActivityController {

    private final ActivityMapper activityMapper;

    public ActivityController(ActivityMapper activityMapper){
        this.activityMapper = activityMapper;
    }

    @PostMapping("api/activity/reserve")
    @ApiOperation("活动预约")
    public Integer ReserveActivity(@RequestBody Map<String,Object> params){
        String status = "待处理";
        String date = params.get("date").toString();
        String usage = params.get("usage").toString();
        String resident_id = params.get("resident_id").toString();
        String startTime = params.get("startTime").toString();
        String endTime = params.get("endTime").toString();
        String room_id = params.get("room_id").toString();
//        room_id = room_id.substring(0,room_id.length()-2);
//        resident_id = resident_id.substring(0,resident_id.length()-2);
        return activityMapper.reserveActivity(new Activity(room_id,resident_id,usage,date,startTime,endTime,status));
    }
   //住户获取所有活动预约（根据room分）
    @GetMapping("api/activity/all")
    @ApiOperation("获取所有活动预约")
    public List<Map<String,Object>> residentFindAllActivity(){
        return activityMapper.findAllActivity();
    }


//    //住户获取所有活动预约（根据room分）
//    @GetMapping("api/activity/all")
//    @ApiOperation("获取所有活动预约")
//    public List<Map<String,Object>> residentFindAllActivity(){
//        return activityMapper.findAllActivity();
//    }


//
//
//    //管理员获取所有活动预约
//    @GetMapping("api/activity/all")
//    @ApiOperation("获取所有活动预约")
//    public List<Map<String,Object>> adminFindAllActivity(){
//        return activityMapper.findallActivity();
//    }
//
//    //管理员设置活动预约的状态
//    @GetMapping("api/activity/all")
//    @ApiOperation("获取所有活动预约")
//    public List<Map<String,Object>> setActivityStatus(){
//        return activityMapper.findallActivity();
//    }



    @GetMapping("/api/activity/cancel")
    @ApiOperation("取消活动预约")
    public Integer deleteActivity(@RequestParam(value ="activity_id", defaultValue = "") String activity_id){
        return activityMapper.deleteActivity(activity_id);
    }
}
