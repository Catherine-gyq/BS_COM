package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AccountMapper;
import com.frankzhu.ems.mapper.ResidentMapper;
import com.frankzhu.ems.model.Resident;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "居民管理")
@RestController
public class ResidentController {

    private final ResidentMapper residentMapper;
    private final AccountMapper accountMapper;
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    @Autowired
    public ResidentController(ResidentMapper residentMapper, AccountMapper accountMapper){
        this.residentMapper = residentMapper;
        this.accountMapper = accountMapper;
    }

    //获取所有的居民信息
    @GetMapping("/api/resident/all")
    @ApiOperation("获取所有的居民信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usr_tele",value = "用户电话",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "name",value = "姓名",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数量",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "String")
    })
    public Map<String, Object> findAllResident(
            @RequestParam(value = "usr_tele", defaultValue = "") String usr_tele,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage){
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> residentInformation  = residentMapper.findAllResidentData(usr_tele,name,allNum,pageSize);
        int totalNum =  residentMapper.findResidentTotalNum(usr_tele,name);
        Map<String, Object> resident =new HashMap<String, Object>();
        resident.put("totalNum", totalNum);
        resident.put("residentInfo",residentInformation);
        return resident;
    }





    //    添加居民
    @PostMapping("/api/resident/add")
    @ApiOperation("添加居民")
    public Integer insertResident(@RequestBody Resident resident) throws NoSuchAlgorithmException {
        String tele = resident.getTele();
        accountMapper.addAccount(tele, md5(tele), "resident");
        return residentMapper.insertResident(resident);
    }

    //更新居民的信息
    @PostMapping("/api/resident/update")
    @ApiOperation("更新居民的信息")
//    修改为Resident类
    public Integer updateResident(@RequestBody Resident resident){
        int id = resident.getId();
        return residentMapper.updateResident(id, resident);
    }


    //删除居民
    @GetMapping("/api/resident/delete")
    @ApiOperation("删除居民")
    @ApiImplicitParam(name = "usr_tele",value = "用户电话",required = true,paramType = "query",dataType = "String")
    public Integer deleteResidentByTele(@RequestParam(value = "tele", defaultValue = "") String tele){
        return residentMapper.deleteResidentByTele(tele);
    }

    //获取居民信息
    @GetMapping("/api/resident/usr")
    @ApiOperation("获取居民信息")
    @ApiImplicitParam(name = "usr_tele",value = "用户电话",required = true,paramType = "query",dataType = "String")
    public Map<String, Object> GetResident(
            @RequestParam(value = "tele", defaultValue = "") String tele){
        return residentMapper.GetResident(tele);
    }



    // md5加密算法
    public String md5(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
        byte[] bytes = md.digest(source.getBytes());
        //将得到的字节数组变成字符串返回
        return byteArrayToHex(bytes).toUpperCase();
    }

    // 将字节数组转换成十六进制，并以字符串的形式返回
    private static String byteArrayToHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            sb.append(byteToHex(value));
        }
        return sb.toString();
    }

    // 将一个字节转换成十六进制，并以字符串的形式返回
    public static String byteToHex(byte b) {
        int n = b;
        if (n < 0)
            n = n + 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexArray[d1]+hexArray[d2];
    }

}
