package entity;

/**
 * Created by nicole on 2018-02-25.
 */

public class Post_comment {
    private String name;
    private String time;
    private int imageId;
    private String body;

    public Post_comment(String name, String time, int imageId, String body){
        this.name = name;
        this.time = time;
        this.imageId= imageId;
        this.body = body;
    }
    public String getName(){
        return name;
    }
    public String getTime(){
        return time;
    }
    public int getImageId(){
        return imageId;
    }
    public String getBody(){
        return body;
    }

}
