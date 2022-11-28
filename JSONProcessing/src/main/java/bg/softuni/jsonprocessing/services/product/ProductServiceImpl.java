package bg.softuni.jsonprocessing.services.product;

import bg.softuni.jsonprocessing.domain.dtos.products.ProductInRangeWithoutBuyerDto;
import bg.softuni.jsonprocessing.domain.dtos.products.SimpleProductDto;
import bg.softuni.jsonprocessing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.jsonprocessing.constants.FilePaths.OUTPUT_URL;
import static bg.softuni.jsonprocessing.constants.FilePaths.PRODUCTS_IN_RANGE_FILE;
import static bg.softuni.jsonprocessing.constants.Utils.MODEL_MAPPER;
import static bg.softuni.jsonprocessing.constants.Utils.writeJsonToFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductInRangeWithoutBuyerDto> findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice
            (float priceFrom, float priceTo) throws IOException {
        List<ProductInRangeWithoutBuyerDto> products = this.productRepository
                .findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice
                        (priceFrom, priceTo)
                .orElseThrow(NoSuchElementException::new)
                .stream().map(p -> MODEL_MAPPER.map(p, SimpleProductDto.class))
                .map(SimpleProductDto::toProductInRangeDTO).toList();

        writeJsonToFile(products, Path.of(OUTPUT_URL + PRODUCTS_IN_RANGE_FILE));

        return products;
    }


}