package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AdviseMapper;
import com.frankzhu.ems.model.Advise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "意见箱管理")
@RestController
public class AdviseController {

    private final AdviseMapper adviseMapper;


    @Autowired
    public AdviseController(AdviseMapper adviseMapper){
        this.adviseMapper = adviseMapper;
    }

    //管理员获取意见箱中所有的意见信息
    @PostMapping("/api/advise/all")
    @ApiOperation("获取意见箱中所有意见")
    public Map<String, Object> findAllAdvise(@RequestBody Map<String, Object> params){
        //参数：pageSize currentPage status
        String pageSize1 = params.get("pageSize").toString();
        String currentPage1 = params.get("currentPage").toString();
        String status = params.get("status").toString();
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> adviseInfo = adviseMapper.adminFindAllAdvise(allNum,pageSize,status);
        Integer totalNum = adviseMapper.adminFindAdviseNum(status);
        Map<String, Object> advise =new HashMap<String, Object>();
        advise.put("totalNum", totalNum);
        advise.put("adviseInfo",adviseInfo);
        return advise;
    }
    //用户获得意见箱中所有意见
    @GetMapping("/api/advise/allInfo")
    @ApiOperation("用户获取意见箱中所有意见")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resident_id",value = "用户id",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数量",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "int")
    })
//可以是自己的也可以是别人的（选项：自己的/所有人）
    public Map<String, Object> findAllAdvise(
            @RequestParam(value = "resident_id", defaultValue = "") String resident_id,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage){
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> adviseInfo = adviseMapper.residentFindAllAdvise(allNum,pageSize,resident_id);
        Integer totalNum = adviseMapper.residentFindAdviseNum(resident_id);
        Map<String, Object> advise =new HashMap<String, Object>();
        advise.put("totalNum", totalNum);
        advise.put("adviseInfo",adviseInfo);
        return advise;
    }


    //添加意见
    @PostMapping("/api/advise/add")
    @ApiOperation("用户添加意见箱中意见")
    public Integer insertAdvice(@RequestBody Advise advise) {
        advise.setStatus("unchecked");
        return adviseMapper.insertAdvice(advise);
    }

    //管理员改变消息状态
    @GetMapping("/api/advise/changeStatus")
    @ApiOperation("管理员改变消息状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "advise_id",value = "意见id",required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "status",value = "意见状态",required = true,paramType = "query",dataType = "String")
    })
        public Integer changeAdviceStatus(
                @RequestParam(value = "advise_id") int advise_id,
                @RequestParam(value = "status", defaultValue = "") String status) {
        return adviseMapper.changeStatus(advise_id,status);
    }

    //通过id获取单条意见的消息
    @GetMapping("/api/advise/detail")
    @ApiOperation("通过意见id获取的内容")
    @ApiImplicitParam(name = "adviseId",value = "adviseId",required = true,paramType = "query",dataType = "String")
    public Map<String, Object> findAdviseById(
            @RequestParam(value = "adviseId", defaultValue = "") String adviseId){
        return adviseMapper.AdviseDataById(adviseId);
    }

    //向意见箱中的意见填写反馈意见(不知道)
    @PostMapping("/api/advise/feedback")
    @ApiOperation("管理员向意见填写反馈意见")
    public Integer adviseFeedback(
            @RequestBody Map<String, Object> params){
        String adviseId = params.get("adviseId").toString();
        String adminId= params.get("adminId").toString();
        String feedback = params.get("feedback").toString();
        return adviseMapper.updateNotice(adviseId,adminId,feedback);
    }

}













