package dao;

import entities.Module;

import javax.persistence.EntityManager;
import java.util.List;

// methods that allow CRUD operations on Module entity
public class ModuleDAO {

    public Module getOne(Long id) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.find(Module.class, id);
    }

    public List<Module> getAll() {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        return em.createQuery("Select m FROM Module m").getResultList();
    }

    public void createOne(Module module) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.persist(module);
        em.getTransaction().commit();
    }

    public void updateOne(Module module) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        em.getTransaction().begin();
        em.merge(module);
        em.getTransaction().commit();
    }

    public void deleteOne(Module module) {
        EntityManager em = EMFClass.getEMFLuc().createEntityManager();
        Module moduleToDelete = em.find(Module.class, module.getId());
        em.getTransaction().begin();
        em.remove(moduleToDelete);
        em.getTransaction().commit();
    }
}
