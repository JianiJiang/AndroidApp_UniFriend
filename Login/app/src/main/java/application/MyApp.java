package application;

/**
 * Created by nicole on 2018-02-26.
 */

import android.app.Application;

import java.util.List;

import entity.Post_ListView;



public class MyApp extends Application {
    public List<Post_ListView> list;

    public List<Post_ListView> getList(){
        return list;
    }
    public void setList(List<Post_ListView> list){
        this.list = list;
    }

}