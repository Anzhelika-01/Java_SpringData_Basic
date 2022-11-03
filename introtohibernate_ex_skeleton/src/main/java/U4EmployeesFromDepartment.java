import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class U4EmployeesFromDepartment {
    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f\n";
    private static final String DEPARTMENT = "Research and Development";

    private static final String QUERY_FOR_FINDING_EMPLOYEES_FROM_DEPARTMENT = """
            SELECT e
            FROM Employee e
            WHERE e.department.name = :dp
            ORDER BY e.salary, e.id
            """;

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        entityManager.createQuery(QUERY_FOR_FINDING_EMPLOYEES_FROM_DEPARTMENT,
                        Employee.class)
                .setParameter("dp", DEPARTMENT)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(), e.getLastName(), e.getDepartment()
                                .getName(), e.getSalary()));

        entityManager.close();
    }
}