package model;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.MySQL;

public class FinancialUserHandler {

    public boolean financelogin(String username, String fullname, String password) {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `users` WHERE `username` = '" + username + "' "
                    + "AND `password_hash`='" + password + "'");
            if (resultSet.next()) {

                if (resultSet.getInt("user_type_id") == 3) {
                    FinancialUserSession.getInstance().setUsername(username);
                    FinancialUserSession.getInstance().setName(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                    return true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}