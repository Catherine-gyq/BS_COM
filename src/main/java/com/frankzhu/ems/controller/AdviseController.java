package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AdviseMapper;
import com.frankzhu.ems.model.Advise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdviseController {

    private final AdviseMapper adviseMapper;


    @Autowired
    public AdviseController(AdviseMapper adviseMapper){
        this.adviseMapper = adviseMapper;
    }

    //获取意见箱中所有的意见信息
    @PostMapping("/api/advise/all")
    public Map<String, Object> findAllAdvise(@RequestBody Map<String, Object> params){
        String pageSize1 = params.get("pageSize").toString();
        String currentPage1 = params.get("currentPage").toString();
        pageSize1 = pageSize1.substring(0,pageSize1.length()-2);
        currentPage1 = currentPage1.substring(0,currentPage1.length()-2);
        Integer pageSize = Integer.valueOf(pageSize1);
        Integer currentPage = Integer.valueOf(currentPage1);
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> adviseInfo = adviseMapper.findAllAdvise(allNum,pageSize);
        Integer totalNum = adviseMapper.findAdviseNum();
        Map<String, Object> advise =new HashMap<String, Object>();
        advise.put("totalNum", totalNum);
        advise.put("residentInfo",adviseInfo);
        return advise;
    }


    //添加消息
    @PostMapping("/api/advise/add")
    public Integer insertAdvice(@RequestBody Map<String, Object> params) {
        String resident_id = params.get("resident_id").toString();
        String datetime = params.get("date").toString();
        String box_title = params.get("title").toString();
        String box_content = params.get("content").toString();
        String status = "待处理";
        return adviseMapper.insertAdvice(new Advise(datetime, resident_id,box_title,box_content,status));
    }

//
//    @PostMapping("/api/open/search")
//    public List<Map<String, Object>> findAllOpenByMu(@RequestBody Map<String, Object> params){
//        String term = params.get("term").toString();
//        String tno = params.get("tno").toString();
//        String tname = params.get("tname").toString();
//        String cno = params.get("cno").toString();
//        String cname = params.get("cname").toString();
//        return openMapper.findAllOpenByMu(term, tno, tname, cno, cname);
//    }

//    @PostMapping("/api/open/update")
//    public Integer updateOpen(@RequestBody Map<String, Object> params){
//        String term = params.get("no").toString();
//        boolean choice = params.get("choice").toString().equals("true");
//        // 同步更新term表
//        voteMapper.updateTerm(term, choice);
//        return openMapper.updateOpen(term, choice);
//    }

//    @GetMapping("/api/open/delete")
//    public Integer deleteOpenByNo(@RequestParam(value = "id", defaultValue = "") int id){
//        return openMapper.deleteOpenByNo(id);
//    }

}
