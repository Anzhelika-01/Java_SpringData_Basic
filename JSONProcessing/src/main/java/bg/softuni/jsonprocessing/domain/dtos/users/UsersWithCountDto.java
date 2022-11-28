package bg.softuni.jsonprocessing.domain.dtos.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersWithCountDto {
    private Integer usersCount;
    private List<UsersWithProductsDto> users;

    public UsersWithCountDto(List<UsersWithProductsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}