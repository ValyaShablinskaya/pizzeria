package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.awt.*;

public class PersonTest {
    @Test
    public void saveTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko");
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (HeadlessException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        em.getTransaction().begin();
        Person personFromDb = em.find(Person.class, person.getId());
        Assert.assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }
    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
