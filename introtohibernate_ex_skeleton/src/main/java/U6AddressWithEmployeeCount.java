import entities.Address;

import javax.persistence.EntityManager;

public class U6AddressWithEmployeeCount {
    public static final String FIND_ALL_ADDRESSES_ORDERED_BY_THE_NUMBER_OF_EMPLOYEES = """
            SELECT a 
            FROM Address a 
            ORDER BY a.employees.size DESC
            """;
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        entityManager.createQuery(FIND_ALL_ADDRESSES_ORDERED_BY_THE_NUMBER_OF_EMPLOYEES,
                        Address.class)
                .setMaxResults(10)
                .getResultList().forEach(System.out::println);

        entityManager.close();
    }
}
