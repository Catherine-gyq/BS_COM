package com.frankzhu.ems.model;

import io.swagger.annotations.ApiModelProperty;

public class Notice {

    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("发布时间")
    private String publish_time;

    @ApiModelProperty("消息类型")
    private String type;

    @ApiModelProperty("内容梗概")
    private String abstracts;

    @ApiModelProperty("具体内容")
    private String content;

    @ApiModelProperty("管理员id")
    private String adminId;

    public Notice(String title, String publish_time,String type ,String abstracts,String content,String adminId){
        this.title = title;
        this.publish_time = publish_time;
        this.type = type;
        this.abstracts = abstracts;
        this.content = content;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type == null ? null : type.trim();
    }
    public String getPublish_time() {
        return publish_time;
    }
    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
    public String getAbstracts(){
        return abstracts;
    }
    public void setAbstracts(String abstracts){
        this.abstracts = abstracts == null ? null : abstracts.trim();
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content == null ? null : content.trim();
    }
    public String getAdminId(){
        return adminId;
    }
    public void setAdminId(String adminId){
        this.adminId = adminId == null ? null : adminId.trim();
    }

}