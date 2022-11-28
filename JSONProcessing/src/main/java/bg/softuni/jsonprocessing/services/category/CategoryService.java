package bg.softuni.jsonprocessing.services.category;

import bg.softuni.jsonprocessing.domain.dtos.categories.CategoryWithProductsCountDto;
import bg.softuni.jsonprocessing.domain.entities.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService{
    Set<Category> getRandomCategories();

    List<CategoryWithProductsCountDto> findAllByCountOfProducts() throws IOException;
}