package nick.pack.service;

import nick.pack.model.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UsersService implements CRUD<Users> {
    @Override
    public Users searchById(int id) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.get(Users.class, id);
        }
    }

    @Override
    public List<Users> read() {
        try(Session session = HibernateUtil.getInstance().openSession()){
            Query<Users> query = session.createQuery("FROM Users");
            return query.list();
        }
    }

    @Override
    public void create(Users users) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Users users) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.beginTransaction();
            session.update(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Users users) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.beginTransaction();
            session.delete(users);
            session.getTransaction().commit();
        }
    }
}
