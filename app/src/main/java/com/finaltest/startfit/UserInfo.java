package com.finaltest.startfit;

public class UserInfo {
    private String name;
    private String photoUrl;

    public UserInfo(String name, String photoUrl){
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public UserInfo(String name){
        this.name = name;
    }



    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

}
