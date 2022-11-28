package bg.softuni.springdataautomappingobjects;

import bg.softuni.springdataautomappingobjects.services.game.GameService;
import bg.softuni.springdataautomappingobjects.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static bg.softuni.springdataautomappingobjects.constants.Commands.*;
import static bg.softuni.springdataautomappingobjects.constants.Validations.COMMAND_NOT_FOUND_MESSAGE;

@Component
public class Main implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    public Main(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tell me what you want: ");
        String[] input = scanner.nextLine().split("\\|");
        while (!input[0].equals("Stop")) {
            String command = input[0];
            switch (command) {
                case REGISTER_USER -> this.userService.registerUser(input);
                case LOGIN_USER -> this.userService.logInUser(input);
                case LOGOUT_USER -> this.userService.logOutUser();
                case ADD_GAME -> this.gameService.addGame(input);
                case EDIT_GAME -> this.gameService.editGame(input);
                case DELETE_GAME -> this.gameService.deleteGame(input);
                case ALL_GAMES -> this.gameService.showAllGames();
                case DETAIL_GAME -> this.gameService.showAllDetailsForGame(input[1]);
                case OWNED_GAMES -> this.gameService.showGamesOwnedByCurrentlyLoggedUser();
                default -> System.out.println(COMMAND_NOT_FOUND_MESSAGE);
            }
            input = scanner.nextLine().split("\\|");
        }
    }
}