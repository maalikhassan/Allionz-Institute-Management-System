package model;

import java.sql.ResultSet;
import model.MySQL;

public class AdminUserHandler {

    public boolean adminlogin(String username, String fullname, String password) {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` WHERE `username` = '" + username + "' "
                    + "AND `password_hash`='" + password + "'");
            if (resultSet.next()) {

                if (resultSet.getInt("user_type_id") == 1) {
                    AdminUserSession.getInstance().setUsername(username);
                    AdminUserSession.getInstance().setName(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                    return true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
