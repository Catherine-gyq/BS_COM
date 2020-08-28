package com.frankzhu.ems.model;

public class Vote {
    private String VD_id;
    private String resident_id;
    private String vote_id;
    //其实是房间能容纳的人的数量
    private String is_agree;

    public Vote(String resident_id, String vote_id, String is_agree){

        this.vote_id = vote_id;
        this.is_agree = is_agree;
        this.resident_id =resident_id;
    }

    public String getVD_id() { return VD_id; }
    public void setVD_id(String VD_id) { this.VD_id = VD_id;}
    public String getResident_id() { return resident_id; }
    public void setResident_id(String resident_id) { this.resident_id = resident_id == null ? null : resident_id.trim(); }
    public String getVote_id() { return vote_id; }
    public void setVote_id(String resident_id) { this.vote_id = resident_id == null ? null : resident_id.trim(); }
    public String getIs_agree(){ return is_agree; }
    public void setIs_agree(String is_agree){ this.is_agree = is_agree == null ? null : is_agree.trim(); }
}
