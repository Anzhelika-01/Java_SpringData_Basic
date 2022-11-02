import javax.persistence.EntityManager;
import java.util.List;

public class U3EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManager entityManager = Utils.getEntityManagerFactory();

        List<String> employees = entityManager
                .createQuery("""
                SELECT firstName
                FROM Employee
                WHERE salary > 50000
                """, String.class).getResultList();

        employees.forEach(System.out::println);
        entityManager.close();
    }
}

