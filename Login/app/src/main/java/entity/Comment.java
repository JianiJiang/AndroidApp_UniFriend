package entity;

/**
 * Created by huangshimin on 2018/2/28.
 */

public class Comment {
    private int id;
    private User user;
    private Post_ListView post;
    private String body;
    private String date;

    public Comment(){

    }
    public Comment(int id, User user, Post_ListView post, String body, String date){
        this.id = id;
        this.user = user;
        this.post = post;
        this.body = body;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post_ListView getPost() {
        return post;
    }

    public void setPost(Post_ListView post) {
        this.post = post;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
