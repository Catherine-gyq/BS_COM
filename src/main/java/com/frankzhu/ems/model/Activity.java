package com.frankzhu.ems.model;

public class Activity {
    private String room_id;
    private String resident_id;
    private String date;
    private String startTime;
    private String endTime;
    private String status;
    private String usage;

    public Activity(String room_id, String resident_id ,String usage, String date, String startTime, String endTime , String status){
        this.room_id = room_id;
        this.resident_id = resident_id;
        this.usage = usage;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getRoom_id() {
        return room_id;
    }
    public void setRoom_id(String room_id) { this.room_id = room_id;}
    public String getResident_id() {
        return resident_id;
    }
    public void setResident_id(String resident_id) { this.resident_id = resident_id;}
    public String getUsage(){return usage;}
    public void setUsage(String usage){ this.usage = usage;}
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }
    public String getEndTime(){
        return endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime == null ? null : endTime.trim();
    }
    public String getStatus(){ return status;}
    public void setStatus(){ this.status = status;}
}
