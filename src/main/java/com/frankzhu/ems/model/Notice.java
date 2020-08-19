package com.frankzhu.ems.model;

public class Notice {

    private int id;          //编号id
    private String title;       // 标题
    private String publish_time;    // 发布时间
    private String content; // 发布内容

    public Notice(String title, String publish_time, String content){
        this.title = title;
        this.publish_time = publish_time;
        this.content = content;
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
    public String getPublish_time() {
        return publish_time;
    }
    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content == null ? null : content.trim();
    }

}