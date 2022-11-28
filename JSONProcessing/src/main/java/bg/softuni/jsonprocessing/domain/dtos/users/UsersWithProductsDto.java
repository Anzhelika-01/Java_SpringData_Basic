package bg.softuni.jsonprocessing.domain.dtos.users;

import bg.softuni.jsonprocessing.domain.dtos.products.SoldProductsWithCountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithProductsDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private SoldProductsWithCountDto soldProducts;
}