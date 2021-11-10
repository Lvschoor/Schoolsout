package dao;


// methods that allow CRUD operations on Grade entity

import model.Grade;

import javax.persistence.EntityManager;
import java.util.List;

public class GradeDAO {
    public Grade getOne(Long id) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(Grade.class, id);
    }

    public List<Grade> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select g FROM Grade g").getResultList();
    }

    public void createOne(Grade grade) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

    public void updateOne(Grade grade) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    public void deleteOne(Grade grade) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Grade gradeToDelete = em.find(Grade.class, grade.getId());
        em.getTransaction().begin();
        em.remove(gradeToDelete);
        em.getTransaction().commit();
    }

}
