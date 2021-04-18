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
    private String mailbox;

    @ApiModelProperty("出生年月")
    private String dateOfBirth;

    public Admin(String name, String sex, String tele,String mailbox,String dateOfBirth){
        this.name = name;
        this.sex = sex;
        this.tele = tele;
        this.mailbox = mailbox;
        this.dateOfBirth = dateOfBirth;
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
    public String getMailbox(){
        return mailbox;
    }
    public void setMailbox(String mailbox){
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth == null ? null : dateOfBirth.trim();
    }
}
