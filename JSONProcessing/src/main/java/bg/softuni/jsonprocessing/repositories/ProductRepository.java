package bg.softuni.jsonprocessing.repositories;

import bg.softuni.jsonprocessing.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice
            (float priceFrom, float priceTo);
}