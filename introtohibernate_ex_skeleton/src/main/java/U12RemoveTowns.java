import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class U12RemoveTowns {
    private static final String DELETE_TOWN_QUERY = """
            SELECT t
            FROM Town AS t
            WHERE t.name = :tn
            """;

    private static final String ADDRESSES_COUNT_QUERY = """
            SELECT a
            FROM Address AS a
            WHERE a.town.name = :tn
            """;

    private static final String PRINT_FORMAT = "%d address%sin %s deleted\n";
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();
        final Scanner scanner = new Scanner(System.in);
        final String townName = scanner.nextLine();

        final Town town = (entityManager.createQuery(DELETE_TOWN_QUERY, Town.class)
                .setParameter("tn", townName).getSingleResult());

        final List<Address> addresses = entityManager
                .createQuery(ADDRESSES_COUNT_QUERY, Address.class)
                .setParameter("tn", townName).getResultList();

        addresses.forEach(address -> {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            address.setTown(null);
            entityManager.remove(address);
        });

        entityManager.remove(town);
        entityManager.getTransaction().commit();

        System.out.printf(PRINT_FORMAT, addresses.size()
                , addresses.size() != 1 ? "es " : " "
                , town.getName());

        entityManager.close();
    }
}