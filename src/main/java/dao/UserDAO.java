package dao;

import entities.Exam;
import entities.User;

import javax.persistence.EntityManager;
import java.util.List;

// methods that allow CRUD operations on User entity

public class UserDAO {

    public User getOne(String login) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(User.class, login);
    }

    public List<User> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select u FROM User u").getResultList();
    }

    public void createOne(User user) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updateOne(User user) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteOne(User user) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Exam userToDelete = em.find(Exam.class, user.getLogin());
        em.getTransaction().begin();
        em.remove(userToDelete);
        em.getTransaction().commit();
    }


}
