package nick.pack.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static org.hibernate.SessionFactory instance;

    public static SessionFactory getInstance(){
        if (instance == null){
            instance = new Configuration().configure().buildSessionFactory();
        }
        return instance;
    }
}
