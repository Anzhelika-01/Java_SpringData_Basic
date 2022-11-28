package bg.softuni.jsonprocessing.services.seed;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException;

    void seedProducts() throws IOException;

    void seedCategories() throws IOException;

    void seedAll() throws IOException;
}