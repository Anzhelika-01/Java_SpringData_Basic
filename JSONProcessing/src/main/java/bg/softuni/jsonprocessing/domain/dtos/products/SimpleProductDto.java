package bg.softuni.jsonprocessing.domain.dtos.products;

import bg.softuni.jsonprocessing.domain.dtos.categories.SimpleCategoryDto;
import bg.softuni.jsonprocessing.domain.dtos.users.SimpleUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProductDto {
    private String name;
    private Float price;

    private SimpleUserDto buyer;
    private SimpleUserDto seller;

    private Set<SimpleCategoryDto> categories;

    public ProductInRangeWithoutBuyerDto toProductInRangeDTO() {
        return new ProductInRangeWithoutBuyerDto
                (name, price, seller.getFullName());
    }

    public static SoldProductsWithCountDto toSoldProductsWithCountDto(Set<SimpleProductDto> soldProducts) {
        return new SoldProductsWithCountDto(soldProducts
                .stream()
                .map(SimpleProductDto::toProductBasicInfoDto)
                .collect(Collectors.toList()));
    }

    public static ProductBasicInfoDto toProductBasicInfoDto(SimpleProductDto simpleProductDto){
        return new ProductBasicInfoDto(simpleProductDto.getName(), simpleProductDto.getPrice());
    }
}