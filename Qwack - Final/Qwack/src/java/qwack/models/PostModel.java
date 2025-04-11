package qwack.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import qwack.Database;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
public class PostModel {

    public static void postQwack(String content, int userID) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `posts` (`id`, `picture`, `likes`, `reqwack`,"
                    + " `timestamp`, `user_id`, `text`) VALUES (NULL, "
                    + "NULL, 0, 0, current_timestamp(), ?, ?)");
            stmt.setInt(1, userID);
            stmt.setString(2, content);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void postQwack(String content, int userID, String pic) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `posts` (`id`, `picture`, `likes`, `reqwack`,"
                    + " `timestamp`, `user_id`, `text`) VALUES (NULL, "
                    + "?, 0, 0, current_timestamp(), ?, ?)");
            stmt.setString(1, pic);
            stmt.setInt(2, userID);
            stmt.setString(3, content);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void reqwackInsert(int postID, int userID) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `reqwack_list` (`user_id`, `post_id`) VALUES (?, ?)");
            stmt.setInt(1, userID);
            stmt.setInt(2, postID);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `posts` SET `reqwack` = `reqwack` + 1, `timestamp` = `timestamp` WHERE `id` = ?");
            stmt.setInt(1, postID);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static ArrayList<Integer> reqwackCheck(int userID) {
        try {
            Connection connection = Database.getConnection();
            ArrayList<Integer> userIDs = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `reqwack_list` WHERE "
                    + "`reqwack_list`.`user_id` = ?");
            stmt.setInt(1, userID);
            ResultSet postIDList = stmt.executeQuery();
            while (postIDList.next()) {
                userIDs.add(postIDList.getInt("post_id"));
            }
            postIDList.close();
            stmt.close();
            return userIDs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
