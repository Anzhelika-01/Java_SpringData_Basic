package bg.softuni.jsonprocessing;

import bg.softuni.jsonprocessing.services.category.CategoryService;
import bg.softuni.jsonprocessing.services.product.ProductService;
import bg.softuni.jsonprocessing.services.seed.SeedService;
import bg.softuni.jsonprocessing.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Main implements CommandLineRunner {
    private final float PRICE_FROM = 500;
    private final float PRICE_TO = 1000;
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public Main(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        this.productService
//                .findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(PRICE_FROM, PRICE_TO);
//        this.userService.findAllBySoldProductsNotNull();
//        this.categoryService.findAllByCountOfProducts();
        this.userService.findAllUsersWithProducts();
    }
}