package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// class to create EntityManager instances

public class EMFClass {

    public static EntityManagerFactory getEMFLuc() {
        return Persistence.createEntityManagerFactory("datasource");
    }
}
