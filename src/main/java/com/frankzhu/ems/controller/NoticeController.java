package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.NoticeMapper;
import com.frankzhu.ems.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeController(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    // 获取所有消息 应该加上消息1
    @GetMapping("/api/notice/all")
    public List<Map<String, Object>> findAllNotice(
            @RequestParam(value = "time", defaultValue = "") String time,
            @RequestParam(value = "name", defaultValue = "") String name){
        return noticeMapper.findAllNotice(time, name);
    }

    @PostMapping("/api/notice/add")
    public Integer insertCourse(@RequestBody Map<String, Object> params) {
        String title = params.get("title").toString();
        String time = params.get("time").toString();
        String id = params.get("admin_id").toString();
//        id = id.substring(0,id.length()-2);
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
