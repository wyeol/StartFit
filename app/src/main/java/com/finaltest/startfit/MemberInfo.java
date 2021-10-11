package com.finaltest.startfit;

import android.widget.EditText;

public class MemberInfo {

    private String name;
    private String height;
    private String weight;
    private String age;
    private String waist;
    private String foot;

    public MemberInfo(String name,String height,String weight, String age, String waist, String foot){
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.waist = waist;
        this.foot = foot;



    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getHeight(){
        return this.height;
    }

    public void setHeight(String height){
        this.height = height;
    }
    public String getWeight(){
        return this.weight;
    }

    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getAge(){
        return this.age;
    }

    public void setAge(String age){
        this.age = age;
    }
    public String getWaist(){
        return this.waist;
    }

    public void setWaist(String waist){
        this.waist = waist;
    }
    public String getFoot(){
        return this.foot;
    }

    public void setFoot(String foot){
        this.foot = foot;
    }
}
