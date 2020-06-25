package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import static jm.task.core.jdbc.util.Util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE users " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age INTEGER not NULL, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE users";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully removed...");
        } catch (SQLException throwables) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection()) {
            String SQL = "INSERT INTO users (name, lastName, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM users WHERE Id =" + id;
            statement.executeUpdate(SQL);
            System.out.printf("User %d removed", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "select * from users";
            ResultSet set = statement.executeQuery(SQL);
            while (set.next()) {
                String name = set.getString(2);
                String lastName = set.getString(3);
                byte age = set.getByte(4);
                list.add(new User(name, lastName, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM users";
            statement.executeUpdate(SQL);
            System.out.println("Table is clear");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
