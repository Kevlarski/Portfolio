/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qwack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Benji Bettagi
 * @author Kevin McLaughlin
 */
public class User implements Serializable {

    private String email_address;
    private String name;
    private String password;
    private String pic;
    private int id;

    public User(String email_address, String name, String password, String pic, int id) {
        this.email_address = email_address;
        this.name = name;
        this.password = password;
        this.pic = pic;
        this.id = id;
    }

    public User() {
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static User pullUser(String email) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `users` WHERE "
                    + "`users`.`email_address` = ?");
            stmt.setString(1, email);
            ResultSet userData = stmt.executeQuery();
            if (userData.next()) {
                User user = new User(userData.getString("email_address"), userData.getString("name"), userData.getString("password"), userData.getString("picture"), userData.getInt("id"));
                userData.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User pullUserFromName(String name) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `users` WHERE "
                    + "`users`.`name` = ?");
            stmt.setString(1, name);
            ResultSet userData = stmt.executeQuery();
            if (userData.next()) {
                User user = new User(userData.getString("email_address"), userData.getString("name"), userData.getString("password"), userData.getString("picture"), userData.getInt("id"));
                userData.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
