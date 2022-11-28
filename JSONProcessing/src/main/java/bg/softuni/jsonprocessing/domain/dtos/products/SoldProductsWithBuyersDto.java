package bg.softuni.jsonprocessing.domain.dtos.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoldProductsWithBuyersDto {
    private String name;

    private Float price;

    private String buyerFirstName;

    private String buyerLastName;
}