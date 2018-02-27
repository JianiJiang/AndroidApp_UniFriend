package entity;

/**
 * Created by huangshimin on 2018/2/27.
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean verified;
    private String uuid;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public boolean getVerified() {
        return verified;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }
}
