package bg.softuni.jsonprocessing.repositories;

import bg.softuni.jsonprocessing.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findById(Long id);
        @Transactional
        @Query("""
                SELECT u FROM User u, Product p
                WHERE p.buyer IS NOT NULL
                AND u.soldProducts.size > 0
                ORDER BY u.lastName, u.firstName
                """)
        Optional<List<User>> findAllBySoldProductsNotNull();

        @Transactional
        @Query("""
                SELECT u
                FROM User u
                WHERE u.soldProducts.size > 0
                GROUP BY u.id
                ORDER BY u.soldProducts.size DESC
                """)
        Optional<List<User>> findAllUsersWithProducts();
}