package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RoomMapper;
import com.frankzhu.ems.model.Resident;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

//    删除该房间
    @GetMapping("/api/room/delete")
    @ApiOperation("删除消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId",value = "房间id",required = true,paramType = "query",dataType = "String"),
    })
    public Integer deleteRoomByNo(@RequestParam(value = "roomId", defaultValue = "") String roomId){
        return roomMapper.deleteRoomById(roomId);
    }

//  添加房间信息


}
