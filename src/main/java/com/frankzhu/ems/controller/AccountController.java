package com.frankzhu.ems.controller;

import com.frankzhu.ems.mapper.AccountMapper;
import com.frankzhu.ems.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "账户管理")
@RestController
public class AccountController {

    private final AccountMapper accountMapper;
    // 十六进制符号
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    @Autowired
    public AccountController(AccountMapper accountMapper){
        this.accountMapper=accountMapper;
    }

    // 密码重置
    @GetMapping("/api/account/resetPwd")
    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名（即电话号码）",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "identity",value = "用户身份",required = true,paramType = "query",dataType = "String"),
    })
    public Integer resetPwd(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "identity") String identity
    ) throws NoSuchAlgorithmException {
        return accountMapper.resetPassword(username, md5(username), identity);
    }

    // 用于用户自己修改密码
    @PostMapping("/api/account/updatePwd")
    @ApiOperation("修改密码")
    public Integer updatePwd(@RequestBody Account account) throws NoSuchAlgorithmException {
        String name = account.getUsername();
        String newPassword = account.getPassword();
        String identity  = account.getIdentity();
//        String name = params.get("name").toString();
//        String newPassword = params.get("newPassword").toString();
//        String identity = params.get("identity").toString();
//        Map<String, Object> ifAccount = accountMapper.findAccountPass(name, md5(oldPassword), identity);
//        int result = 0;
//        if (ifAccount == null){
//            result = 0;
//        }else{
//            result = accountMapper.resetPassword(name, md5(newPassword),identity);
//        }
//        return result;
        return accountMapper.resetPassword(name, md5(newPassword),identity);
    }

    // 用于用户的登录
    @PostMapping("/api/account/login")
    @ApiOperation("用户登录")
    public Integer login(@RequestBody Account account) throws NoSuchAlgorithmException {
        String name = account.getUsername();
        String password = account.getPassword();
        String identity = account.getIdentity();

        Map<String, Object> ifAccount = accountMapper.findAccount(name, identity);
        int result = 0;
        if (ifAccount == null){
            result = 0;
        }else{
            Map<String, Object> ifPassword = accountMapper.findAccountPass(name, md5(password), identity);
            if (ifPassword == null){
                result = 1;
            }else{
                result = 2;
            }
        }
        return result;
    }

    // 获取用户自身的所有的信息
    @GetMapping("/api/account/allInform")
    @ApiOperation("获取用户的所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名（即电话号码）",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "identity",value = "用户身份",required = true,paramType = "query",dataType = "String"),
    })
    public Map<String,Object> getUserInform(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "identity") String identity
    ){
        Map<String,Object> allInfo = new HashMap<String, Object>();
        if (identity=="resident"){
            allInfo =  accountMapper.getResidentInformation(username,identity);
        }
        else if (identity=="admin" || identity=="super_admin"){
            allInfo =  accountMapper.getAdminInformation(username,identity);
        }
        return allInfo;
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
