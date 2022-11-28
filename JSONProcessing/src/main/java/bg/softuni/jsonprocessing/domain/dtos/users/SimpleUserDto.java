package bg.softuni.jsonprocessing.domain.dtos.users;

import bg.softuni.jsonprocessing.domain.dtos.products.SimpleProductDto;
import bg.softuni.jsonprocessing.domain.dtos.products.SoldProductsWithCountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static bg.softuni.jsonprocessing.domain.dtos.products.SimpleProductDto.toSoldProductsWithCountDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDto {
    private String firstName;
    private String lastName;

    private Integer age;

    private Set<SimpleProductDto> soldProducts;

    private Set<SimpleProductDto> boughtProducts;

    private Set<SimpleUserDto> friends;
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UsersWithProductsDto toUserWithProductsDto() {
        return new UsersWithProductsDto(firstName, lastName, age, toSoldProductsWithCountDto(soldProducts));
    }
}