package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String SQL = "CREATE TABLE IF NOT EXISTS users " +
                "(Id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age INTEGER not NULL, " +
                "PRIMARY KEY (id))";
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(SQL).executeUpdate();
        transaction.commit();
        System.out.println("Table successfully created...");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        transaction.commit();
        System.out.println("Table deleted...");
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createCriteria(User.class).list();
        transaction.commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        transaction.commit();
        System.out.println("Table is clear");
        session.close();
    }
}
