package bg.softuni.jsonprocessing.domain.dtos.users;

import bg.softuni.jsonprocessing.domain.dtos.products.SoldProductsWithBuyersDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersWithSoldProductsAndBuyerDto {
    private String firstName;

    private String lastName;

    private List<SoldProductsWithBuyersDto> soldProducts;

    private String buyerFirstName;

    private String buyerLastName;
}