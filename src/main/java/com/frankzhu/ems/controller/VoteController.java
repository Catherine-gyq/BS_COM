package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.VoteMapper;
import com.frankzhu.ems.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class VoteController {

    private final VoteMapper voteMapper;

    @Autowired
    public VoteController(VoteMapper voteMapper){
        this.voteMapper = voteMapper;
    }

    //获取所有的投票信息
    @GetMapping("/api/vote/all")
    public List<Map<String, Object>> findAllVote(){
        return voteMapper.findAllVote();
    }

    //生成相应的投票记录
    @PostMapping("/api/vote/add")
    public Integer insertVote(@RequestBody Map<String, Object> params) {
        String resident_id = params.get("resident_id").toString();
        String vote_id = params.get("vote_id").toString();
        String is_agree = params.get("is_agree").toString();
        return voteMapper.insertVote(new Vote(resident_id,vote_id,is_agree));
    }

    // 获取所有该消息的同意与不同意的数量
    @GetMapping("/api/vote/count")
    public List<Map<String,Object>> VoteCount(@RequestParam(value = "vote_id", defaultValue = "") String vote_id){
        return voteMapper.voteCount(vote_id);
    }
}
