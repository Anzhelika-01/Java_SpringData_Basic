package bg.softuni.jsonprocessing.domain.dtos.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SoldProductsWithCountDto {
    private Integer count;
    private List<ProductBasicInfoDto> products;

    public SoldProductsWithCountDto(List<ProductBasicInfoDto> products) {
        this.products = products;
        this.count = products.size();
    }
}