import javax.persistence.EntityManager;
import java.util.List;

public class U3EmployeesWithSalaryOver50000 {
    private static final String EMPLOYEE_WITH_GIVEN_SALARY_QUERY = """
            SELECT firstName
            FROM Employee
            WHERE salary > 50000
            """;

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        final List<String> employees = entityManager
                .createQuery(EMPLOYEE_WITH_GIVEN_SALARY_QUERY, String.class).getResultList();

        employees.forEach(System.out::println);
        entityManager.close();
    }
}

