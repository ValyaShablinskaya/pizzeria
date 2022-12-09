package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;
    static {
        emFactory = Persistence.createEntityManagerFactory("by.it_academy.jd2.mk_jd2_92_22.pizzeria");
    }
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
    public static void close() {
        emFactory.close();
    }
}
