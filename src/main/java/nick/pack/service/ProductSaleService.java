package nick.pack.service;

import nick.pack.model.ProductSale;
import org.hibernate.Session;

import java.util.List;

public class ProductSaleService implements CRUD<ProductSale> {
    @Override
    public ProductSale searchById(int id) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.get(ProductSale.class, id);
        }
    }

    @Override
    public List<ProductSale> read() {
        try(Session session = HibernateUtil.getInstance().openSession()){
            return session.createQuery("FROM ProductSale").list();
        }
    }

    @Override
    public void create(ProductSale entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(ProductSale entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ProductSale entity) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
