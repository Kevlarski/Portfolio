package qwack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
public class Post implements Serializable {

    private String caption;
    private String pic;
    private String timestamp;
    private int likes;
    private int reqwack;
    private int id;
    private int userID;
    private User user;

    public Post(String caption, String pic, String timestamp, int likes, int reqwack, int id, int userID, User user) {
        this.caption = caption;
        this.pic = pic;
        this.timestamp = timestamp;
        this.likes = likes;
        this.reqwack = reqwack;
        this.id = id;
        this.userID = userID;
        this.user = user;
    }

    public Post() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReqwack() {
        return reqwack;
    }

    public void setReqwack(int reqwack) {
        this.reqwack = reqwack;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

}
