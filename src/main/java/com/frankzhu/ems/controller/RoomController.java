package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RoomMapper;
import com.frankzhu.ems.model.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    private final RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

//    获取所有的房间信息
    @GetMapping("api/room/all")
    public List<Map<String, Object>> FindAllRoom(){
        return roomMapper.findAllRoom();
    }


}
