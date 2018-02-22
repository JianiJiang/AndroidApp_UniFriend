package com.example.jiani.login.activity;

/**
 * Created by nicole on 2018-02-21.
 */

public class Main_page_post {
    private String title;
    private String body;
    private int imageId;
    private String time;
    public Main_page_post(String title, String body, int imageId, String time){
        this.title = title;
        this.imageId = imageId;
        this.body = body;
        this.time = time;
    }
    public String getTitle(){
        return title;
    }
    public String getBody(){
        return body;
    }
    public int getImageId(){
        return imageId;
    }
    public String getTime(){
        return time;
    }
}
