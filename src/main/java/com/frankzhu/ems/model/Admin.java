package com.frankzhu.ems.model;

public class Admin {

    private int id;
    private String name;
    private String sex;
    private String tele;

    public Admin(String name, String sex, String tele){
        this.name = name;
        this.sex = sex;
        this.tele = tele;

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
}
