package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.ActivityMapper;
import com.frankzhu.ems.model.Activity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ActivityController {

    private final ActivityMapper activityMapper;

    public ActivityController(ActivityMapper activityMapper){
        this.activityMapper = activityMapper;
    }

    @PostMapping("api/activity/reserve")
    public Integer ReserveActivity(@RequestBody Map<String,Object> params){
        String status = "待处理";
        String date = params.get("date").toString();
        String usage = params.get("usage").toString();
        String resident_id = params.get("resident_id").toString();
        String startTime = params.get("startTime").toString();
        String endTime = params.get("endTime").toString();
        String room_id = params.get("room_id").toString();
        room_id = room_id.substring(0,room_id.length()-2);
        resident_id = resident_id.substring(0,resident_id.length()-2);
        return activityMapper.reserveActivity(new Activity(room_id,resident_id,usage,date,startTime,endTime,status));
    }

    @GetMapping("api/activity/all")
    public List<Map<String,Object>> FindAllActivity(){
        return activityMapper.findallActivity();
    }

    @GetMapping("/api/activity/cancel")
    public Integer deleteActivity(@RequestParam(value ="activity_id", defaultValue = "") String activity_id){
        return activityMapper.deleteActivity(activity_id);
    }
}
