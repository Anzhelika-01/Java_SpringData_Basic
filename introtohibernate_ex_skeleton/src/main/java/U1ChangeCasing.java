import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class U1ChangeCasing {
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        final List<Town> resultList = entityManager
                .createQuery("SELECT t FROM Town AS t", Town.class)
                .getResultList();

        for (Town town : resultList) {
            final String townName = town.getName();

            if (townName.length() <= 5) {
                town.setName(townName.toUpperCase());

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

