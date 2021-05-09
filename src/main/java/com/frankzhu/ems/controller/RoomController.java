package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.RoomMapper;
import com.frankzhu.ems.model.Advise;
import com.frankzhu.ems.model.Resident;
import com.frankzhu.ems.model.Room;
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
    @ApiOperation("删除房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId",value = "房间id",required = true,paramType = "query",dataType = "String"),
    })
    public Integer deleteRoomByNo(@RequestParam(value = "roomId", defaultValue = "") String roomId){
        return roomMapper.deleteRoomById(roomId);
    }
//   根据id查询房间的信息


//  添加房间信息
    @PostMapping("/api/room/add")
    @ApiOperation("用户添加意见箱中意见")
    public Integer insertAdvice(@RequestBody Room room) {
        return roomMapper.insertRoom(room);
    }

//    修改房间信息
    @PostMapping("/api/room/update")
    @ApiOperation("管理员向意见填写反馈意见")
    public Integer adviseFeedback(
            @RequestBody Room room){
//        String adviseId = params.get("adviseId").toString();
//        String adminId= params.get("adminId").toString();
//        String feedback = params.get("feedback").toString();
        return roomMapper.updateRoom(room);
    }
}
