import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class U9IncreaseSalaries {

    private static final String SELECT_EMPLOYEES_FROM_DEPARTMENTS_QUERY = """
            SELECT e
            FROM Employee AS e
            WHERE e.department.name IN
            ('Engineering', 'Tool Design', 'Marketing', 'Information Services')
            """;

    private static final String PRINT_FORMAT = "%s %s ($%.2f)\n";

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        entityManager.createQuery(SELECT_EMPLOYEES_FROM_DEPARTMENTS_QUERY, Employee.class)
                .getResultList()
                .forEach(employee -> employee.setSalary(employee.getSalary()
                        .multiply(BigDecimal.valueOf(1.12))));

        entityManager.createQuery(SELECT_EMPLOYEES_FROM_DEPARTMENTS_QUERY, Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT
                        , e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.close();
    }
}