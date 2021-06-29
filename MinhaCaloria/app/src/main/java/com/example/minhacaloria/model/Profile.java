package com.example.minhacaloria.model;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile {
    private final String name;
    private final String sex;
    private final String objective;
    private final String update;
    private final Integer age;
    private final Integer user_id;
    private final Integer tbm;
    private final Integer target;
    private final Double weight;
    private final Double height;

    public Profile(Integer user_id, String name, String sex, Double weight, Double height, Integer age, String objective){
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.objective = objective;
        this.user_id = user_id;
        this.weight = weight;
        this.height = height;
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.update = dateFormat.format(new Date());


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
                this.target = this.tbm + 800;
                break;
            default:
                this.target = this.tbm;
                break;
        }
    }

    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public String getObjective() {
        return objective;
    }
    public String getUpdate() {
        return update;
    }
    public Integer getAge() {
        return age;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public Integer getTbm() {
        return tbm;
    }
    public Integer getTarget() {
        return target;
    }
    public Double getWeight() {
        return weight;
    }
    public Double getHeight() {
        return height;
    }
}
