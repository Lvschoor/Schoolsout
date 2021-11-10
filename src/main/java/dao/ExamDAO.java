package dao;

import model.Exam;

import javax.persistence.EntityManager;
import java.util.List;

// methods that allow CRUD operations on Exam entity

public class ExamDAO {

    public Exam getOne(Long id) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(Exam.class, id);
    }

    public List<Exam> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select e FROM Exam e").getResultList();
    }

    public void createOne(Exam exam) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(exam);
        em.getTransaction().commit();
    }

    public void updateOne(Exam exam) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(exam);
        em.getTransaction().commit();
    }

    public void deleteOne(Exam exam) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Exam examToDelete = em.find(Exam.class, exam.getId());
        em.getTransaction().begin();
        em.remove(examToDelete);
        em.getTransaction().commit();
    }
}
