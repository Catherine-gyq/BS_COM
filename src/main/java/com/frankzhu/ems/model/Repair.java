package com.frankzhu.ems.model;

public class Repair {

    private String id;
    private String repair_time;
    private String resident_id;
    private String admin_id;
    private String repair_address;
    private String repair_content;
    private String status;

    public Repair(String repair_time, String resident_id,String repair_address,String repair_content,String status){
        this.repair_time = repair_time;
        this.resident_id = resident_id;
        this.repair_address = repair_address;
        this.repair_content = repair_content;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRepair_time() {
        return repair_time;
    }
    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
    }
    public String getResident_id() {return resident_id; }
    public void setResident_id(String resident_id) { this.resident_id = resident_id == null ? null : resident_id.trim(); }
    public String getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id == null ? null : admin_id.trim();
    }
    public String getRepair_address() {
        return repair_address;
    }
    public void setRepair_address(String repair_address) {
        this.repair_address = repair_address == null ? null : repair_address.trim();
    }
    public String getRepair_content(){return repair_content;}
    public void setRepair_content(){ this.repair_content = repair_content == null ? null : repair_content.trim(); }
    public String getStatus(){return status;}
    public void setStatus(){ this.status = status == null ? null : status.trim(); }
}
