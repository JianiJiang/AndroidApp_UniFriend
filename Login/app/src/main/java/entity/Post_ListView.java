package entity;

/**
 * Created by nicole on 2018-02-26.
 */

    public class Post_ListView {
        private String title;
        private String body;
        private Long id;
        private User user;
        private int imageId;
        private boolean anonymous;
        private int likes;
        private int dislikes;
        private String date;


        public Post_ListView(Long id, User user, String title, String body, boolean anonymous, int likes, int dislikes, String date){
            this.id = id;
            this.user = user;
            this.title = title;
            this.body = body;
            this.anonymous = anonymous;
            this.likes = likes;
            this.dislikes = dislikes;
            this.date = date;
        }

    public Post_ListView() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
