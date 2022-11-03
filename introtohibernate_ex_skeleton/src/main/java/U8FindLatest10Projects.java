import entities.Project;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;

public class U8FindLatest10Projects {
    private static final String LATEST_PROJECT_QUERY = """
            SELECT p
            FROM Project AS p
            ORDER BY p.name, p.startDate DESC
            """;
    private static final String PRINT_FORMAT = """
            Project name: %s
            Project Description: %s
            Project Start Date:%s
            Project End Date: %s
            """;

    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.getEntityManagerFactory();

        entityManager.createQuery(LATEST_PROJECT_QUERY, Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(e -> System.out.printf
                        (PRINT_FORMAT, e.getName(), e.getDescription()
                        , e.getStartDate().format(formatter), e.getEndDate()));
        entityManager.close();
    }
}