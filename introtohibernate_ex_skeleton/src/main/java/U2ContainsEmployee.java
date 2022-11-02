import javax.persistence.EntityManager;
import java.util.Scanner;

public class U2ContainsEmployee {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[] fullName = scanner.nextLine().split("\\s+");
        final String firstName = fullName[0];
        final String lastName = fullName[1];

        EntityManager entityManager = Utils.getEntityManagerFactory();

        final Long query = entityManager
                .createQuery("""
                                SELECT COUNT(e)
                                FROM Employee AS e
                                WHERE e.firstName = :fn AND e.lastName = :ln
                                """,
                        Long.class).setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if (query == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.close();
    }
}

