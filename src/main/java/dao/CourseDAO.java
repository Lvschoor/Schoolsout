package dao;

import model.Course;

import javax.persistence.EntityManager;
import java.util.List;

// methods that allow CRUD operations on Course entity

public class CourseDAO {

    public Course getOne(Long id) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(Course.class, id);
    }

    public List<Course> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select c FROM Course c").getResultList();
    }

    public void createOne(Course course) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    public void updateOne(Course course) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    public void deleteOne(Course course) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Course courseToDelete = em.find(Course.class, course.getId());
        em.getTransaction().begin();
        em.remove(courseToDelete);
        em.getTransaction().commit();
    }
}
