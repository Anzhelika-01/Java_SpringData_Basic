import entities.Address;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class U5AddingANewAddressAndUpdatingEmployee {
    private static final String SET_ADDRESS_QUERY = """
            UPDATE Employee AS e
            SET e.address = :newAddress
            WHERE e.lastName = :ln
            """;
    private static final String ADDRESS = "Vitoshka 15";

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();
        final Scanner scanner = new Scanner(System.in);
        final String lastName = scanner.nextLine();

        final Address address = new Address();
        address.setText(ADDRESS);
        entityManager.persist(address);

        entityManager.createQuery(SET_ADDRESS_QUERY)
                .setParameter("newAddress", address)
                .setParameter("ln", lastName);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}