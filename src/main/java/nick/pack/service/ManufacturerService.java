package nick.pack.service;

import nick.pack.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ManufacturerService implements CRUD<Manufacturer>{
    @Override
    public Manufacturer searchById(int id) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.get(Manufacturer.class, id);
        }
    }

    @Override
    public List<Manufacturer> read() {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.createQuery("FROM Manufacturer").list();
        }
    }

    @Override
    public void create(Manufacturer entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Manufacturer entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Manufacturer entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
