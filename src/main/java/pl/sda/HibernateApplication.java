package pl.sda;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApplication {

    public static void main(String[] args) {
        final SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
    }
}
