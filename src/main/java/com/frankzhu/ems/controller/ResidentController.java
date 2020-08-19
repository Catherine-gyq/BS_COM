package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AccountMapper;
import com.frankzhu.ems.mapper.ResidentMapper;
import com.frankzhu.ems.model.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
//import org.apache.commons.lang.StringUtils;

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

    @GetMapping("/api/resident/all")
    public List<Map<String, Object>> findAllResident(
            @RequestParam(value = "usr_tele", defaultValue = "") String usr_tele,
            @RequestParam(value = "name", defaultValue = "") String name){
        return residentMapper.findAllResident(usr_tele, name);
    }

    @PostMapping("/api/resident/add")
    public Integer insertResident(@RequestBody Map<String, Object> params) throws NoSuchAlgorithmException {
        String name = params.get("name").toString();
        String sex = params.get("sex").toString();
        String tele = params.get("tele").toString();
        String address = params.get("address").toString();
        // 同步创建一个账号(密码是手机号)
        accountMapper.addAccount(tele, md5(tele), "resident");
        return residentMapper.insertResident(new Resident(tele, name, sex, address));
    }

    //
    @PostMapping("/api/resident/update")
    public Integer updateResident(@RequestBody Map<String, Object> params){
        String id = params.get("resident_id").toString();
        id = id.substring(0,id.length()-2);
        String name = params.get("name").toString();
        String sex = params.get("sex").toString();
        String tele = params.get("tele").toString();
        String address = params.get("address").toString();
//        accountMapper.updateAccounttele(tele);
        return residentMapper.updateResident(id, new Resident(tele, name, sex, address));
    }


    //删除居民
    @GetMapping("/api/resident/delete")
    public Integer deleteResidentByTele(@RequestParam(value = "tele", defaultValue = "") String tele){
        System.out.println(tele);
        return residentMapper.deleteResidentByTele(tele);
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
