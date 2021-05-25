package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AccountMapper;
import com.frankzhu.ems.mapper.AdminMapper;
import com.frankzhu.ems.model.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "管理员管理")
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
    @ApiOperation("获取管理员自身的信息")
    @ApiImplicitParam(name = "tele",value = "用户电话",required = true,paramType = "query",dataType = "String")
    public List<Map<String, Object>> GetAdmin(
            @RequestParam(value = "tele", defaultValue = "") String tele){
        return adminMapper.GetAdmin(tele);
    }

    //获取所有管理员的信息
    @GetMapping("/api/admin/all")
    @ApiOperation("获取所有的管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tele",value = "用户电话",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "name",value = "姓名",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "单页数量",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,paramType = "query",dataType = "String")
    })
    public Map<String, Object> GetAdminInfo(
            @RequestParam(value = "tele", defaultValue = "") String tele,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageSize", defaultValue = "") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "") Integer currentPage){
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
    @ApiOperation("删除")
    @ApiImplicitParam(name = "usr_tele",value = "用户电话",required = true,paramType = "query",dataType = "String")
    public Integer deleteResidentByTele(@RequestParam(value = "tele", defaultValue = "") String tele){
        int temp = adminMapper.deleteAdminByTele(tele);
        if (temp==1){
            return accountMapper.deleteAccountByTele(tele);
        }else{
            return 0;
        }
    }

    //添加头像图片
    @PostMapping("/api/admin/upload")
    @ApiOperation("添加头像图片")
//    @ApiImplicitParam(name = "usr_tele",value = "用户电话",required = true,paramType = "query",dataType = "String")
    public Integer uploadAvatar(@RequestParam(required = true) MultipartFile image,
                                HttpServletRequest request) throws IOException {
        String basePath = request.getServletContext().getRealPath("upload/");
//        System.out.println(basePath);
//        在这里把路径设置为电脑中的路径
        System.out.println(basePath+image.getName());
        File directory = new File(basePath,image.getName());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            image.transferTo(directory);
        } catch (Exception e) {
            System.out.println(e);
        }

        String dir = directory.getCanonicalPath();
        System.out.println(dir);
        return 1;
    }

    //  添加管理员
    @PostMapping("/api/admin/add")
    @ApiOperation("添加管理员")
    public Integer insertResident(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        String tele = admin.getTele();
        String identity = admin.getIdentity();
        accountMapper.addAccount(tele, md5(tele),identity);
        return adminMapper.insertAdmin(admin);
    }

    //更新管理员的信息
    @PostMapping("/api/admin/update")
    @ApiOperation("更新管理员信息")
    public Integer updateResident(@RequestBody Admin admin){
        int id = admin.getId();
        return adminMapper.updateAdmin(id,admin);
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
