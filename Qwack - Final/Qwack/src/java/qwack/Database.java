package qwack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import qwack.models.PostModel;

/* @author Benji & Kevin*/
public class Database {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/qwack";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(dbURL,
                    username, password);
            return connection;
        } catch (Exception e) {
            System.out.println("*+*+*+*+*" + e.getStackTrace() + "*+*+*+*+*");
        }
        return null;
    }

    public static ArrayList<Post> postList() {
        try {
            Connection connection = Database.getConnection();
            ArrayList<Post> postList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `posts` ORDER BY timestamp DESC");
            ResultSet posts = stmt.executeQuery();
            while (posts.next()) {
                User user = userInfo(posts.getInt("user_id"));
                Post post = new Post(posts.getString("text"), posts.getString("picture"), posts.getString("timestamp"), posts.getInt("likes"), posts.getInt("reqwack"), posts.getInt("id"), posts.getInt("user_id"), user);
                postList.add(post);
            }
            posts.close();
            return postList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<Post> postList(int userID) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt;
            ArrayList<Integer> postIDs = PostModel.reqwackCheck(userID);
            ArrayList<Post> postList = new ArrayList<>();
            int test = postIDs.size();
            int j = 2;
            if (!postIDs.isEmpty() && postIDs != null) {
                String query = ("SELECT * FROM `posts` WHERE "
                        + "`posts`.`user_id` = ? OR `posts`.`id` IN (?");
                for (int i = 1; i < postIDs.size(); i++) {
                    query += ",?";
                }
                query += ") ORDER BY timestamp DESC";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, userID);
                for (int i = 0; i < (postIDs.size()); i++) {
                    stmt.setInt(j, postIDs.get(i));
                j++;
                }

            } else {
                String query = ("SELECT * FROM `posts` WHERE `posts`.`user_id` = ? ORDER BY timestamp DESC");
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, userID);
            }
            ResultSet posts = stmt.executeQuery();
            while (posts.next()) {
                User user = userInfo(posts.getInt("user_id"));
                Post post = new Post(posts.getString("text"), posts.getString("picture"), posts.getString("timestamp"), posts.getInt("likes"), posts.getInt("reqwack"), posts.getInt("id"), posts.getInt("user_id"), user);
                postList.add(post);
            }
            posts.close();
            return postList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<Post> postList(User user) {
        ArrayList<Integer> userIDs = followingIDs(user.getId());
        try {
            Connection connection = Database.getConnection();
            ArrayList<Post> postList = new ArrayList<>();

            String query = ("SELECT * FROM `posts` WHERE `posts`.`user_id` IN (?");
            for (int i = 1; i < userIDs.size(); i++) {
                query += ",?";
            }
            query += ") ORDER BY timestamp DESC";
            PreparedStatement stmt = connection.prepareStatement(query);
            for (int i = 0; i < userIDs.size(); i++) {
                stmt.setInt(i + 1, userIDs.get(i));
            }
            ResultSet posts = stmt.executeQuery();
            while (posts.next()) {
                User profile = userInfo(posts.getInt("user_id"));
                Post post = new Post(posts.getString("text"), posts.getString("picture"), posts.getString("timestamp"), posts.getInt("likes"), posts.getInt("reqwack"), posts.getInt("id"), posts.getInt("user_id"), profile);
                postList.add(post);
            }
            posts.close();
            return postList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<Integer> followingIDs(int followingID) {
        try {
            Connection connection = Database.getConnection();
            ArrayList<Integer> userIDs = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `follow_list` WHERE "
                    + "`follow_list`.`following_id` = ?");
            stmt.setInt(1, followingID);
            ResultSet idList = stmt.executeQuery();
            while (idList.next()) {
                userIDs.add(idList.getInt("followed_id"));
            }
            idList.close();
            return userIDs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User userInfo(int id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `users` WHERE "
                    + "`users`.`id` = ?");
            stmt.setInt(1, id);
            ResultSet userData = stmt.executeQuery();
            if (userData.next()) {
                User user = new User(userData.getString("email_address"), userData.getString("name"), userData.getString("password"), userData.getString("picture"), userData.getInt("id"));
                userData.close();
                return user;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void picUpdate(int userID, String pic) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET picture = ? WHERE id = ?");
            stmt.setString(1, pic);
            stmt.setInt(2, userID);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static int likeCount(int id) {
        try {
            int likes = 1;
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `likes` FROM `posts` WHERE "
                    + "`posts`.`id` = ?");
            stmt.setInt(1, id);
            ResultSet likeCount = stmt.executeQuery();
            while (likeCount.next()) {
                likes = likeCount.getInt("likes");
            }
            likeCount.close();
            return likes;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void likePost(int id) {
        int likeCount = likeCount(id);
        likeCount++;
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE posts SET likes = ?, `timestamp` = `timestamp` WHERE id = ?");
            stmt.setInt(1, likeCount);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
