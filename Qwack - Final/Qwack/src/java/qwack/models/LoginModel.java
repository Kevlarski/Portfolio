/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qwack.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import qwack.Database;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
// */
public class LoginModel {

    public static String login(String email, String password) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `email_address`, `password` FROM `users` WHERE "
                    + "`users`.`email_address` = ? AND `users`.`password` = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    return rs.getString("email_address");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "false";
    }
    
    public static void register(String name, String email, String password){
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `users` (`id`, `name`, `password`, `email_address`, `picture`) VALUES (NULL, "
                    + "?, ?, ?, ?)");
            stmt.setString(1, name.toLowerCase());
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, "views/assets/default.png");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
//    private static String hashPassword(String passwordToHash,
//            String salt) {
//        String generatedPassword = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt.getBytes());
//            byte[] bytes = md.digest(passwordToHash.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < bytes.length; i++) {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
//                        .substring(1));
//            }
//            generatedPassword = sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//    }
//
//    // Add salt
//    private static String getSalt() throws NoSuchAlgorithmException {
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return salt.toString();
//    }

}
