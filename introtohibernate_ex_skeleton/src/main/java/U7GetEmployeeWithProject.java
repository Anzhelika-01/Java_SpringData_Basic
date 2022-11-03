import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.stream.Collectors;

public class U7GetEmployeeWithProject {
    private static final String GET_EMPLOYEE_BY_ID_QUERY = """
            SELECT e
            FROM Employee AS e
            WHERE e.id = :id
            GROUP BY e.firstName, e.lastName
            """;

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();
        final Scanner scanner = new Scanner(System.in);

        final int id = Integer.parseInt(scanner.nextLine());

        entityManager.createQuery(GET_EMPLOYEE_BY_ID_QUERY, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .forEach(e -> System.out.printf(e.getFirstName()
                        + " " + e.getLastName() +
                        " - " + e.getJobTitle() + "\n" +
                        e.getProjects().stream().map(Project::getName)
                                .sorted()
                                .collect(Collectors.joining(System.lineSeparator()))));
    }
}