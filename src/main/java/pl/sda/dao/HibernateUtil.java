package pl.sda.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class HibernateUtil {

    private final Session session;
    private final Class clazz;

    public HibernateUtil(EntityManager entityManager, Class clazz) {
        this.session = entityManager.unwrap(Session.class);
        this.clazz = clazz;
    }

    public List<Object> findAll() {
        return session.createQuery("From " + clazz.getName(), clazz).getResultList();
    }

    public Object findById(Long id) {
        Query query = session.createNamedQuery("findById", clazz);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public void printAll() {
        System.out.println("Dane z tabeli: ");
        List<Object> entities = findAll();
        for (Object entity : entities) {
            System.out.println(entity);
        }
    }

    public void create(Object objectToCreate) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.persist(objectToCreate);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void delete(Long id) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.remove(session.find(clazz, id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void close() {
        session.close();
    }
}
