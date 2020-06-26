package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Иван", "Иванов", (byte) 23);
        userDao.saveUser("Петр", "Петров", (byte) 33);
        userDao.saveUser("Сидор", "Сидоров", (byte) 43);
        userDao.saveUser("Сергей", "Сергеев", (byte) 53);
        List<User> list = userDao.getAllUsers();
        for (User user : list
        ) {
            System.out.println(user);
        }
        List<User> list2 = userDao.getAllUsers();
        //userDao.cleanUsersTable();
        userDao.removeUserById(1);
        for (User user: list2
             ) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
