package entity;

import android.util.Log;

/**
 * Created by huangshimin on 2018/2/28.
 */

public class SendComment {
    private String body;
    private String username;
    private Long postID;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
