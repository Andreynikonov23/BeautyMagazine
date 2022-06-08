package nick.pack.service;

import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.model.ProductSale;
import org.hibernate.Session;

import java.util.List;

public class ProductService implements CRUD<Product> {
    @Override
    public Product searchById(int id) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.get(Product.class, id);
        }
    }

    @Override
    public List<Product> read() {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.createQuery("FROM Product").list();
        }
    }

    @Override
    public void create(Product entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Product entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Product entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
