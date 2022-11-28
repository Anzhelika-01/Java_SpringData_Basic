package bg.softuni.jsonprocessing.domain.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithProductsCountDto {
    private String category;

    private Long productsCount;

    private Double averagePrice;

    private Double totalRevenue;
}