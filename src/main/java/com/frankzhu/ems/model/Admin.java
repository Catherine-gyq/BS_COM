package com.frankzhu.ems.model;

public class Admin {

    private int id;
    private String name;
    private String sex;
    private String tele;
    private String mailbox;
    private String dateOfBirth;

    public Admin(String name, String sex, String tele,String mailbox,String dateOfBirth){
        this.name = name;
        this.sex = sex;
        this.tele = tele;
        this.mailbox = mailbox;
        this.dateOfBirth = dateOfBirth;
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
    public String getMailbox(){
        return mailbox;
    }
    public void setMailbox(String mailbox){
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth == null ? null : dateOfBirth.trim();
    }
}
