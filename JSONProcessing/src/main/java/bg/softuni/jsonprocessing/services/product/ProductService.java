package bg.softuni.jsonprocessing.services.product;

import bg.softuni.jsonprocessing.domain.dtos.products.ProductInRangeWithoutBuyerDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductInRangeWithoutBuyerDto> findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice
            (float priceFrom, float priceTo) throws IOException;
}