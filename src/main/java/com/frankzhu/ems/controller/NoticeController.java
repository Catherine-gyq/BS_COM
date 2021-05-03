package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.NoticeMapper;
import com.frankzhu.ems.model.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "消息管理")
@RestController
public class NoticeController {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeController(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    // 获取所有消息 应该加上消息类型
    @PostMapping("/api/notice/all")
    @ApiOperation("获取所有的社区消息")
    public Map<String, Object> findAllNotice(@RequestBody Map<String, Object> params){
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
        String type = params.get("type").toString();
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        int totalNum = 0;
        List<Map<String, Object>> noticeInformation = new ArrayList();

        //如果没有选择类型
        if(type.equals("allType")){
            totalNum = noticeMapper.findNoticeNum(name,startTime,endTime);
            noticeInformation = noticeMapper.findNoticeData(name,startTime,endTime,allNum,pageSize);
        }else{
            totalNum =  noticeMapper.findNoticeNumByType(name,type,startTime,endTime);
            noticeInformation  = noticeMapper.findNoticeDataByType(name,type,startTime,endTime,allNum,pageSize);
        }
        Map<String, Object> notice =new HashMap<String, Object>();
        notice.put("totalNum", totalNum);
        notice.put("noticeInfo",noticeInformation);
        return notice;
    }

    @PostMapping("/api/notice/add")
    @ApiOperation("增加消息")
    public Integer insertCourse(@RequestBody Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    //修改消息
    @PostMapping("/api/notice/update")
    @ApiOperation("修改消息")
    public Integer updateCourse(@RequestBody Notice notice){
        int id = notice.getId();
        return noticeMapper.updateNotice(notice,id);
    }

    @GetMapping("/api/notice/delete")
    @ApiOperation("删除消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "消息id",required = true,paramType = "query",dataType = "String"),
    })
    public Integer deleteCourseByNo(@RequestParam(value = "id", defaultValue = "") String id){
        return noticeMapper.deleteNoticeById(id);
    }



}
