import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

enum Utils {
    ;

    static EntityManager getEntityManagerFactory() {

        final EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }
}