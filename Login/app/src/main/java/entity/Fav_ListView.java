package entity;

/**
 * Created by nicole on 2018-02-26.
 */

public class Fav_ListView {
    private int id;
    private Post_ListView post;
    private User user;


    public Fav_ListView() {
    }

    public Fav_ListView(int id, Post_ListView post, User user){
        this.id = id;
        this.post = post;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post_ListView getPost() {
        return post;
    }

    public void setPost(Post_ListView post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
