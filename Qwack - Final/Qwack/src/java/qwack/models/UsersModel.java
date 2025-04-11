package qwack.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import qwack.Database;
import qwack.User;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
public class UsersModel {

    /**
     * @param following
     * @param followed
     */
    public static void follow(String following, String followed) throws SQLException {
        User followedUser = User.pullUserFromName(followed);
        User followingUser = User.pullUserFromName(following);
        if (!checkFollow(following, followed).next()) {
            try {
                Connection connection = Database.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO `follow_list` (`id`, `following_id`, `followed_id`) VALUES (NULL, ?, ?)");
                stmt.setInt(1, followingUser.getId());
                stmt.setInt(2, followedUser.getId());
                stmt.execute();
                
            } catch (Exception ex) {
                Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Followed "+followed);
        } else {
            try {
                Connection connection = Database.getConnection();
                ArrayList<Integer> followedArray = new ArrayList();
                int i = 0;
                ResultSet rs = checkFollow(following, followed);
                PreparedStatement stmt = connection.prepareStatement(
                            "DELETE FROM `follow_list` WHERE `follow_list`.`id` = ?");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    stmt.setInt(1, id);
                    stmt.execute();
                }
                
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Unfollowed "+followed);
        }
        
    }

    public static ResultSet checkFollow(String following, String followed) {
        try {
            User followedUser = User.pullUserFromName(followed);
            User followingUser = User.pullUserFromName(following);
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SElECT `follow_list`.`id` FROM `follow_list` WHERE (`following_id` = ? AND `followed_id` = ?)");
            stmt.setInt(1, followingUser.getId());
            stmt.setInt(2, followedUser.getId());
            ResultSet resultSet = stmt.executeQuery();
            
            return resultSet;
        } catch (Exception ex) {
            Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
