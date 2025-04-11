package qwack.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import qwack.Database;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
public class ProfileModel {

    public static void updateUser(String name, int user_id, String picPath) {
        System.out.println("both " + user_id);
    }

    public static void updateUser(int user_id, String picPath) {
        System.out.println("just pic " + user_id);
    }

    public static void updateUser(String name, int user_id) {
        System.out.println("just name " + user_id);
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                   "UPDATE `users` SET `name` = ? WHERE `users`.`id` = ?");
            stmt.setInt(2, user_id);
            stmt.setString(1, name);
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            Logger.getLogger(PostModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
