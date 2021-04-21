package com.frankzhu.ems.model;

import io.swagger.annotations.ApiModelProperty;

public class Advise {

    @ApiModelProperty(value = "id",name="id",required=false)
    private String id;

    @ApiModelProperty(value = "发表时间",name="dateTime",required=false)
    private String dateTime;

    @ApiModelProperty(value = "用户id",name="resident_id",required=false)
    private String resident_id;

    @ApiModelProperty("意见标题")
    private String title;

    @ApiModelProperty("意见内容")
    private String content;

    @ApiModelProperty("状态")
    private String status;

    public Advise(String dateTime, String resident_id, String title, String content, String status){
        this.dateTime = dateTime;
        this.resident_id = resident_id;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getResident_id() {
        return resident_id;
    }
    public void setResident_id(String resident_id) {
        this.resident_id = resident_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String box_title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
