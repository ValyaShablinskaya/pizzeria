package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;

import javax.persistence.EntityManager;

public class PersonLoader {
    public static void main(String[] args) {
        Person person = new Person(null, 35, "Yuli", "Slabko");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}
