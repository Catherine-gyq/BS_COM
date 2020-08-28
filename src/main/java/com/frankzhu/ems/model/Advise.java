package com.frankzhu.ems.model;

public class Advise {

    private String id;
    private String datetime;
    private String resident_id;
    private String box_title;
    private String box_content;
    private String status;

    public Advise(String datetime, String resident_id, String box_title, String box_content, String status){
        this.datetime = datetime;
        this.resident_id = resident_id;
        this.box_title = box_title;
        this.box_content = box_content;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public String getResident_id() {
        return resident_id;
    }
    public void setResident_id(String resident_id) {
        this.resident_id = resident_id;
    }
    public String getBox_title() {
        return box_title;
    }
    public void setBox_title(String box_title) {
        this.box_title = box_title;
    }
    public String getBox_content() {
        return box_content;
    }
    public void setBox_content(String box_content) {
        this.box_content = box_content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
