import javax.persistence.EntityManager;

public class U11EmployeesMaximumSalary {
    private static final String MAX_SALARY_QUERY = """
            SELECT e.department.name, MAX(e.salary)
            FROM  Employee AS e
            GROUP BY e.department
            HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000
            """;

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        entityManager
                .createQuery(MAX_SALARY_QUERY, Object[].class).getResultList()
                .forEach(e -> System.out.println(e[0] + " " + e[1]));

        entityManager.close();
    }
}