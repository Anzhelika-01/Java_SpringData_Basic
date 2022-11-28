package bg.softuni.jsonprocessing.services.user;

import bg.softuni.jsonprocessing.domain.dtos.users.SimpleUserDto;
import bg.softuni.jsonprocessing.domain.dtos.users.UsersWithCountDto;
import bg.softuni.jsonprocessing.domain.dtos.users.UsersWithProductsDto;
import bg.softuni.jsonprocessing.domain.dtos.users.UsersWithSoldProductsAndBuyerDto;
import bg.softuni.jsonprocessing.domain.entities.User;
import bg.softuni.jsonprocessing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static bg.softuni.jsonprocessing.constants.FilePaths.*;
import static bg.softuni.jsonprocessing.constants.Utils.MODEL_MAPPER;
import static bg.softuni.jsonprocessing.constants.Utils.writeJsonToFile;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getRandomUser() {
        final long count = this.userRepository.count();

        if (count != 0) {
            final long randomUserId = new Random()
                    .nextLong(1L, count) + 1L;
            return this.userRepository.findById(randomUserId)
                    .orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException("No users seeded.");
    }

    @Override
    public List<UsersWithSoldProductsAndBuyerDto> findAllBySoldProductsNotNull() throws IOException {
        final List<UsersWithSoldProductsAndBuyerDto> users = this.userRepository
                .findAllBySoldProductsNotNull()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER
                        .map(user, UsersWithSoldProductsAndBuyerDto.class))
                .toList();

        writeJsonToFile(users, Path.of(OUTPUT_URL + USERS_WITH_SOLD_PRODUCTS_FILE));

        return users;
    }

    @Override
    public List<UsersWithProductsDto> findAllUsersWithProducts() throws IOException {
        final List<UsersWithProductsDto> users = this.userRepository
                .findAllUsersWithProducts()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, SimpleUserDto.class))
                .map(SimpleUserDto::toUserWithProductsDto)
                .toList();

        writeJsonToFile(users, Path.of(OUTPUT_URL + USERS_AND_PRODUCTS));
        return users;
    }
}