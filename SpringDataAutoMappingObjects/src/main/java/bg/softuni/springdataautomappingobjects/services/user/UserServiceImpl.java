package bg.softuni.springdataautomappingobjects.services.user;

import bg.softuni.springdataautomappingobjects.domain.dtos.LoginDTO;
import bg.softuni.springdataautomappingobjects.domain.dtos.RegistrationDTO;
import bg.softuni.springdataautomappingobjects.domain.entities.Game;
import bg.softuni.springdataautomappingobjects.domain.entities.User;
import bg.softuni.springdataautomappingobjects.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.softuni.springdataautomappingobjects.constants.Validations.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private User loggedInUser;
    private final ModelMapper modelMapper = new ModelMapper();
    public User user;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserFirst() {
        return this.userRepository.count() == 0;
    }

    @Override
    public void registerUser(String[] input) {
        String email = input[1];
        String password = input[2];
        String confirmPassword = input[3];
        String fullName = input[4];

        if (!isUserRegistered(email)) {
            RegistrationDTO registrationDTO = new RegistrationDTO
                    (email, password, confirmPassword, fullName);
            final User user = this.modelMapper.map(registrationDTO, User.class);
            user.setAdministrator(isUserFirst());
            userRepository.save(user);
            System.out.printf(SUCCESSFULLY_REGISTERED, fullName);
        } else {
            throw new IllegalArgumentException(EMAIL_ALREADY_EXIST);
        }
    }

    @Override
    public boolean isUserRegistered(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void logInUser(String[] input) {
        String email = input[1];
        String password = input[2];

        LoginDTO loginDTO = new LoginDTO(email, password);
        Optional<User> user = this.userRepository.findByEmail(loginDTO.getEmail());

        if (isUserRegistered(email) && this.loggedInUser == null &&
                user.get().getPassword().equals(loginDTO.getPassword())) {

            this.loggedInUser = this.userRepository.findByEmail(loginDTO.getEmail()).get();
            System.out.println(SUCCESSFULLY_LOGGED_IN + this.loggedInUser.getFullName());
        } else if (this.loggedInUser != null) {
            throw new IllegalArgumentException(SOMEONE_ALREADY_LOGGED);
        } else {
            throw new IllegalArgumentException(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return this.loggedInUser != null;
    }

    @Override
    public void logOutUser() {
        if (isUserLoggedIn()) {
            System.out.printf(SUCCESSFULLY_LOGGED_OUT, this.loggedInUser.getFullName());
            this.loggedInUser = null;
        } else {
            System.out.println(NO_LOGGED_USER);
        }
    }
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    @Override
    public String ownedGames() {
        List<String> collect = this.user.getGames().stream().map(Game::getTitle).toList();
        StringBuilder sb  = new StringBuilder();
        for (String s : collect) {
            sb.append(String.format("%s%n",s));
        }
        return sb.toString().trim();
    }
}