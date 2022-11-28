package bg.softuni.springdataautomappingobjects.services.user;

import bg.softuni.springdataautomappingobjects.domain.dtos.LoginDTO;
import bg.softuni.springdataautomappingobjects.domain.dtos.RegistrationDTO;
import bg.softuni.springdataautomappingobjects.domain.entities.User;

import java.util.Optional;

public interface UserService {
    void registerUser(String[] input);

    boolean isUserFirst();

    boolean isUserRegistered(String email);

    void logInUser(String[] input);

    boolean isUserLoggedIn();

    void logOutUser();

    User getLoggedInUser();

    String ownedGames();
}