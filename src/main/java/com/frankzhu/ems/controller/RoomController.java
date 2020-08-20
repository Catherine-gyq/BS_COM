package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    private final RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @GetMapping("api/room/all")
    public List<Map<String, Object>> FindAllRoom(){
//        System.out.println("获取房间信息");
        return roomMapper.findAllRoom();
    }

}
