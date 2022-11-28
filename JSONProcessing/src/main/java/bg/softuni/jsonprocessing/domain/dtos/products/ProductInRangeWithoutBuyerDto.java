package bg.softuni.jsonprocessing.domain.dtos.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInRangeWithoutBuyerDto {
    private String name;

    private Float price;

    private String seller;
}