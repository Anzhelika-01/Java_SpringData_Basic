package bg.softuni.jsonprocessing.services.category;

import bg.softuni.jsonprocessing.domain.dtos.categories.CategoryWithProductsCountDto;
import bg.softuni.jsonprocessing.domain.entities.Category;
import bg.softuni.jsonprocessing.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import static bg.softuni.jsonprocessing.constants.FilePaths.CATEGORIES_BY_PRODUCTS_COUNT;
import static bg.softuni.jsonprocessing.constants.FilePaths.OUTPUT_URL;
import static bg.softuni.jsonprocessing.constants.Utils.writeJsonToFile;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        final long count = this.categoryRepository.count();

        if (count != 0) {
            final long randomCategoryId = new Random().nextLong(1L, count) + 1L;

            return Set.of(this.categoryRepository
                    .findById(randomCategoryId)
                    .orElseThrow(NoSuchElementException::new));
        }
        throw new RuntimeException();
    }

    @Override
    @Transactional
    public List<CategoryWithProductsCountDto> findAllByCountOfProducts() throws IOException {
        List<CategoryWithProductsCountDto> products =
                this.categoryRepository.findAllByCountOfProducts()
                        .orElseThrow(NoSuchElementException::new);

        writeJsonToFile(products, Path.of(OUTPUT_URL + CATEGORIES_BY_PRODUCTS_COUNT));
        return products;
    }
}
