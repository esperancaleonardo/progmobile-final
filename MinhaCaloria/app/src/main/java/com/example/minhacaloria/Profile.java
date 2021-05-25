package com.example.minhacaloria;

import java.util.Date;

public class Profile {
    private String name, sex, objective, update;
    private Integer age, user_id, tbm, target;
    private Double weight, height;

    public Profile(Integer user_id, String name, String sex, Double weight, Double height, Integer age, String objective){
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.objective = objective;
        this.user_id = user_id;
        this.weight = weight;
        this.height = height;
        this.update = String.valueOf(new Date());

        if(sex.equals("M")){
            this.tbm = (int) Math.round(66.5 + (13.75*this.weight) + (5*this.height) - (6.8*this.age));
        }else{
            this.tbm = (int) Math.round(665.1 + (9.56*this.weight) + (1.8*this.height) - (4.7*this.age));
        }

        switch (objective){
            case "Perder":
                this.target = this.tbm - 600;
                break;
            case "Ganhar":
                this.target = this.tbm + 600;
                break;
            default:
                this.target = this.tbm;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTbm() {
        return tbm;
    }

    public void setTbm(Integer tbm) {
        this.tbm = tbm;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
