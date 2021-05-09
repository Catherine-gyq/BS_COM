package com.frankzhu.ems.model;

public class Room {
    private int id;
    private String usage;
    private String picture;
    //其实是房间能容纳的人的数量
    private String num;
    private String address;
    private String description;

    public Room(String name, String picture, String num, String address,String description){
        this.usage = name;
        this.picture = picture;
        this.num = num;
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id;}
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage == null ? null : usage.trim();
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
    public String getNum(){
        return num;
    }
    public void setNum(String num){
        this.num = num == null ? null : num.trim();
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address == null ? null : address.trim();
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description == null ? null : description.trim();
    }
}
