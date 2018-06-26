package com.huat.qi.appfirst.model;

/**
 * Created by Qi on 2018/5/10.
 */

public class Player {
    private String name;
    private String password;
    private String grade;
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Player(String name,String password){
//        this._id = _id;
        this.name=name;
        this.password=password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        String result = "";
        result += "玩家：" + this.name + "，";
        result += "成绩：" + this.grade + "，";
        return result;
    }
}
