package com.frankzhu.ems.model;

import io.swagger.annotations.ApiModelProperty;

public class Admin {
    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("电话")
    private String tele;

    @ApiModelProperty("邮箱")
    private String mailBox;

    @ApiModelProperty("出生年月")
    private String dateOfBirth;

    @ApiModelProperty("个人权限")
    private String identity;

    public Admin(String name, String sex, String tele,String mailBox,String dateOfBirth,String identity){
        this.name = name;
        this.sex = sex;
        this.tele = tele;
        this.mailBox = mailBox;
        this.dateOfBirth = dateOfBirth;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id;}
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
    public String getMailBox(){
        return mailBox;
    }
    public void setMailBox(String mailBox){
        this.mailBox = mailBox == null ? null : mailBox.trim();
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth == null ? null : dateOfBirth.trim();
    }
    public String getIdentity(){
        return identity;
    }
    public void setIdentity(String identity){
        this.identity = identity == null ? null : identity.trim();
    }
}
