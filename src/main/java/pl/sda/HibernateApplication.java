package pl.sda;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.dao.HibernateUtil;
import pl.sda.domain.Address;
import pl.sda.domain.Person;

import java.time.LocalDate;

public class HibernateApplication {

    public static void main(String[] args) {
        final SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Person.class)
                        .addAnnotatedClass(Address.class)
                        .buildSessionFactory();

        HibernateUtil personDao = new HibernateUtil(sessionFactory.createEntityManager(),
                Person.class);

        Address address = new Address("city", "street", "20");
        Person person = new Person("firstName", "lastName", 30, 80, LocalDate.now(), address);

        personDao.create(person);

        personDao.printAll();

    }
}
