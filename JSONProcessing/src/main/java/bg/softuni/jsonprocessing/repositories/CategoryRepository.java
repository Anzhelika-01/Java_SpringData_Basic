package bg.softuni.jsonprocessing.repositories;

import bg.softuni.jsonprocessing.domain.dtos.categories.CategoryWithProductsCountDto;
import bg.softuni.jsonprocessing.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    @Query("""
            SELECT NEW bg.softuni.jsonprocessing.domain.dtos.categories.CategoryWithProductsCountDto
            (c.name, COUNT(p), AVG(p.price), SUM(p.price))
            FROM Product p JOIN p.categories c
            GROUP BY c.id
            ORDER BY COUNT(p)
            """)
    Optional<List<CategoryWithProductsCountDto>> findAllByCountOfProducts();
}