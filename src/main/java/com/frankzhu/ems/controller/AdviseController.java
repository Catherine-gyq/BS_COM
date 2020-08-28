package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AdviseMapper;
import com.frankzhu.ems.model.Advise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdviseController {

    private final AdviseMapper adviseMapper;
//    private final VoteMapper voteMapper;

    @Autowired
    public AdviseController(AdviseMapper adviseMapper){
        this.adviseMapper = adviseMapper;
    }

    //获取意见箱中所有的意见信息
    @GetMapping("/api/advise/all")
    public List<Map<String, Object>> findAllAdvise(){
        return adviseMapper.findAllAdvise();
    }


    //添加消息
    @PostMapping("/api/advise/add")
    public Integer insertAdvice(@RequestBody Map<String, Object> params) {
        String datetime = params.get("box_time").toString();
        String resident_id = params.get("resident_id").toString();
        String box_title = params.get("box_title").toString();
        String box_content = params.get("box_content").toString();
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
