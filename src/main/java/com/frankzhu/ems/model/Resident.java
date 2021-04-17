package com.frankzhu.ems.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("学生类")
public class Resident {
    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("电话")
    private String tele;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("邮箱")
    private String mailBox;

//    id自增
    public Resident(String tele, String name, String sex, String address, String mailBox){
        this.name = name;
        this.sex = sex;
        this.tele = tele;
        this.address = address;
        this.mailBox = mailBox;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
    public String getTele(){
        return tele;
    }
    public void setTele(String tele){
        this.tele = tele == null ? null : tele.trim();
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address == null ? null : address.trim();
    }
    public String getMailBox(){
        return mailBox;
    }
    public void setMailBox(String mailBox){
        this.mailBox = mailBox == null ? null : mailBox.trim();
    }

}
