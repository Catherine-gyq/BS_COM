package com.frankzhu.ems.model;

public class Resident {

    private int id;
    private String tele;
    private String name;
    private String sex;
    private String address;
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
