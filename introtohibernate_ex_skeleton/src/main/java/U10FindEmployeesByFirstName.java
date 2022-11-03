import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class U10FindEmployeesByFirstName {
    private static final String FIND_EMPLOYEES_BY_FIRST_NAME = """
            SELECT e
            FROM Employee AS e
            WHERE LOWER(SUBSTRING(e.firstName,1,2)) = LOWER(:ss)
            """;

    private static final String PRINT_FORMAT = "%s %s - %s - ($%.2f)\n";

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        final Scanner scanner = new Scanner(System.in);
        final String pattern = scanner.nextLine();

        entityManager.createQuery(FIND_EMPLOYEES_BY_FIRST_NAME, Employee.class)
                .setParameter("ss", pattern)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(), e.getLastName(), e.getJobTitle(),
                        e.getSalary()));
    }
}