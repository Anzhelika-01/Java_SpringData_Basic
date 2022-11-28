package bg.softuni.jsonprocessing.services.seed;

import bg.softuni.jsonprocessing.domain.dtos.categories.ImportCategoryDto;
import bg.softuni.jsonprocessing.domain.dtos.products.ImportProductDto;
import bg.softuni.jsonprocessing.domain.dtos.users.ImportUserDto;
import bg.softuni.jsonprocessing.domain.entities.Category;
import bg.softuni.jsonprocessing.domain.entities.Product;
import bg.softuni.jsonprocessing.domain.entities.User;
import bg.softuni.jsonprocessing.repositories.CategoryRepository;
import bg.softuni.jsonprocessing.repositories.ProductRepository;
import bg.softuni.jsonprocessing.repositories.UserRepository;
import bg.softuni.jsonprocessing.services.category.CategoryService;
import bg.softuni.jsonprocessing.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

import static bg.softuni.jsonprocessing.constants.FilePaths.*;
import static bg.softuni.jsonprocessing.constants.Utils.GSON;
import static bg.softuni.jsonprocessing.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    FileReader fileReader;
    private final UserService userService;

    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository, UserService userService, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {
            fileReader = new FileReader
                    (RESOURCE_URL + USER_FILE_NAME);

            ImportUserDto[] createUser = GSON.fromJson
                    (fileReader, ImportUserDto[].class);

            for (ImportUserDto userDTO : createUser) {
                final User user = MODEL_MAPPER.map(userDTO, User.class);
                userRepository.save(user);
            }
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            fileReader = new FileReader
                    (RESOURCE_URL + CATEGORY_FILE_NAME);

            ImportCategoryDto[] createCategory = GSON.fromJson
                    (fileReader, ImportCategoryDto[].class);

            for (ImportCategoryDto categoryDTO : createCategory) {
                final Category category = MODEL_MAPPER
                        .map(categoryDTO, Category.class);
                categoryRepository.save(category);
            }
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() == 0) {
            fileReader = new FileReader
                    (RESOURCE_URL + PRODUCT_FILE_NAME);

            ImportProductDto[] createProduct = GSON.fromJson
                    (fileReader, ImportProductDto[].class);

            for (ImportProductDto productDTO : createProduct) {
                final Product product = MODEL_MAPPER
                        .map(productDTO, Product.class);
                product.setSeller(this.userService.getRandomUser());

                if (product.getPrice() < 700) {
                    product.setBuyer(this.userService.getRandomUser());
                }
                product.setCategories(this.categoryService.getRandomCategories());
                productRepository.save(product);
            }
            fileReader.close();
        }
    }

    @Override
    public void seedAll() throws IOException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}