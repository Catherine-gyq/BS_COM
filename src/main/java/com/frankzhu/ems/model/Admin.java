package com.frankzhu.ems.model;

public class Admin {

    private int id;
    private String name;
    private String sex;
    private String birthday;
    private String education;
    private String department;

    public Admin(String name, String sex, String birthday, String education, String department){
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.education = education;
        this.department = department;
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
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday == null ? null : birthday.trim();
    }
    public String getEducation(){
        return education;
    }
    public void setEducation(String education){
        this.education = education == null ? null : education.trim();
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department == null ? null : department.trim();
    }

}
