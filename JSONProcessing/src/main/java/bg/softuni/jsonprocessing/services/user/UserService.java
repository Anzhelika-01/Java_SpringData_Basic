package bg.softuni.jsonprocessing.services.user;

import bg.softuni.jsonprocessing.domain.dtos.users.UsersWithProductsDto;
import bg.softuni.jsonprocessing.domain.dtos.users.UsersWithSoldProductsAndBuyerDto;
import bg.softuni.jsonprocessing.domain.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User getRandomUser();

    List<UsersWithSoldProductsAndBuyerDto> findAllBySoldProductsNotNull() throws IOException;

    List<UsersWithProductsDto> findAllUsersWithProducts() throws IOException;
}
