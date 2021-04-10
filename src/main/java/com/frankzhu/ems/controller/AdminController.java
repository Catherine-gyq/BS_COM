package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AccountMapper;
import com.frankzhu.ems.mapper.AdminMapper;
import com.frankzhu.ems.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    private final AdminMapper adminMapper;
    private final AccountMapper accountMapper;
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    @Autowired
    public AdminController(AdminMapper adminMapper, AccountMapper accountMapper){
        this.adminMapper = adminMapper;
        this.accountMapper = accountMapper;
    }

    //获取管理员自身的信息
    @GetMapping("/api/admin/usr")
    public List<Map<String, Object>> GetAdmin(
            @RequestParam(value = "tele", defaultValue = "") String tele){
        return adminMapper.GetAdmin(tele);
    }

    //获取所有管理员的信息
    @GetMapping("/api/admin/all")
    public Map<String, Object> GetAdminInfo(
            @RequestParam(value = "tele", defaultValue = "") String tele,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageSize", defaultValue = "") int pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") int currentPage){
        Integer allNum = pageSize*(currentPage-1);
        List<Map<String, Object>> adminInfo  = adminMapper.GetAdminInfo(tele,name,pageSize,allNum);
        int totalNum =  adminMapper.getAdminTotalNum(tele,name);
        Map<String, Object> admin =new HashMap<String, Object>();
        admin.put("totalNum", totalNum);
        admin.put("adminInfo",adminInfo);
        return admin;
    }

    //删除该管理员
    @GetMapping("/api/admin/delete")
    public Integer deleteResidentByTele(@RequestParam(value = "tele", defaultValue = "") String tele){
        return adminMapper.deleteAdminByTele(tele);
    }

    //  添加居民
    @PostMapping("/api/admin/add")
    public Integer insertResident(@RequestBody Map<String, Object> params) throws NoSuchAlgorithmException {
        String name = params.get("name").toString();
        String sex = params.get("sex").toString();
        String tele = params.get("tele").toString();
        String mailBox = params.get("mailBox").toString();
        String dateOfBirth = params.get("dateOfBirth").toString();
        accountMapper.addAccount(tele, md5(tele), "admin");
        return adminMapper.insertAdmin(new Admin(tele, name, sex, mailBox,dateOfBirth));
    }

    //更新管理员的信息
    @PostMapping("/api/admin/update")
    public Integer updateResident(@RequestBody Map<String, Object> params){
        String id = params.get("resident_id").toString();
        id = id.substring(0,id.length()-2);
        String name = params.get("name").toString();
        String sex = params.get("sex").toString();
        String tele = params.get("tele").toString();
        String mailBox = params.get("mailBox").toString();
        String dateOfBirth = params.get("dateOfBirth").toString();
        return adminMapper.updateAdmin(id, new Admin(tele, name, sex,mailBox,dateOfBirth));
    }


//    @GetMapping("/api/admin/usr")
//    public void GetAdmin(
//            @RequestParam(value = "tele", defaultValue = "") String tele){
//            System.out.println(adminMapper.GetAdmin(tele));
//    }




//    @GetMapping("/api/teacher/all")
//    public List<Map<String, Object>> findAllTeacher(
//            @RequestParam(value = "no", defaultValue = "") String no,
//            @RequestParam(value = "name", defaultValue = "") String name){
//        return adminMapper.findAllTeacher(no, name);
//    }

//    @GetMapping("/api/teacher/search")
//    public List<Map<String, Object>> findStudentByDe(@RequestParam(value = "de", defaultValue = "") String de){
//        return adminMapper.findTeacherByDe(de);
//    }
////
//    @PostMapping("/api/teacher/add")
//    public Integer insertTeacher(@RequestBody Map<String, Object> params) throws NoSuchAlgorithmException {
//        String name = params.get("name").toString();
//        String no = params.get("no").toString();
//        String sex = params.get("sex").toString();
//        String birthday = params.get("birthday").toString();
//        String education = params.get("education").toString();
//        String department = params.get("departmentID").toString();
//        // 同步创建一个账号
//        accountMapper.addAccount(no, md5(no), "teacher");
//        return teacherMapper.insertTeacher(new Teacher(no, name, sex, birthday, education, department));
//    }

//    @PostMapping("/api/teacher/update")
//    public Integer updateTeacher(@RequestBody Map<String, Object> params){
//        String name = params.get("name").toString();
//        String no = params.get("no").toString();
//        String sex = params.get("sex").toString();
//        String birthday = params.get("birthday").toString();
//        String education = params.get("education").toString();
//        String department = params.get("departmentID").toString();
//        return teacherMapper.updateTeacher(new Teacher(no, name, sex, birthday, education, department));
//    }

//    @GetMapping("/api/teacher/delete")
//    public Integer deleteTeacherByNo(@RequestParam(value = "no", defaultValue = "") String no){
//        return adminMapper.deleteTeacherByNo(no);
//    }

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
