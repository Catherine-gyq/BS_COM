package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.NoticeMapper;
import com.frankzhu.ems.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeController(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    // 获取所有消息 应该加上消息类型
    // 有些是非必须属性
    @PostMapping("/api/notice/all")
    public Map<String, Object> findAllNotice(@RequestBody Map<String, Object> params){
//        System.out.println(params);
        String name = params.get("name").toString();
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

        pageSize1 = pageSize1.substring(0,pageSize1.length()-2);
        currentPage1 = currentPage1.substring(0,currentPage1.length()-2);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        int totalNum =  noticeMapper.findAllNoticeTotalNum(name,startTime,endTime);
        List<Map<String, Object>> noticeInformation  = noticeMapper.findAllNoticeData(name,startTime,endTime,allNum,pageSize);
        Map<String, Object> notice =new HashMap<String, Object>();
        notice.put("totalNum", totalNum);
        notice.put("residentInfo",noticeInformation);
        return notice;
    }

    @PostMapping("/api/notice/add")
    public Integer insertCourse(@RequestBody Map<String, Object> params) {
        String title = params.get("title").toString();
        String time = params.get("time").toString();
        String id = params.get("admin_id").toString();
        String content = params.get("content").toString();
        System.out.println(id);
        return noticeMapper.insertNotice(new Notice(title,time,content),id);
    }

    //修改消息
    @PostMapping("/api/notice/update")
    public Integer updateCourse(@RequestBody Map<String, Object> params){
        String id = params.get("id").toString();
        id = id.substring(0,id.length()-2);
        String title = params.get("title").toString();
        String time = params.get("time").toString();
        String content = params.get("content").toString();
        return noticeMapper.updateNotice(new Notice(title, time, content),id);
    }

    @GetMapping("/api/notice/delete")
    public Integer deleteCourseByNo(@RequestParam(value = "id", defaultValue = "") String id){
        return noticeMapper.deleteNoticeById(id);
    }



}
