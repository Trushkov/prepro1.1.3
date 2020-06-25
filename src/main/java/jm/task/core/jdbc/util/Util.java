package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb1?serverTimezone=Europe/Moscow&useSSL=false";
        String userName = "root";
        String password = "zxc123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Connection failed...");
            System.out.println(e);
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, userName, password);
    }
}
