package dao;

import entities.Person;

import javax.persistence.EntityManager;
import java.util.List;
// methods that allow CRUD operations on Person entity

public class PersonDAO {

    public Person getOne(Integer id) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(Person.class, id);
    }

    public List<Person> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select p FROM Person p").getResultList();
    }

    public void createOne(Person person) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    public void updateOne(Person person) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
    }

    public void deleteOne(Person person) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Person personToDelete = em.find(Person.class, person.getId());
        em.getTransaction().begin();
        em.remove(personToDelete);
        em.getTransaction().commit();
    }

}