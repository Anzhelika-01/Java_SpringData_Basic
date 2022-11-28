package bg.softuni.springdataautomappingobjects.services.game;

import bg.softuni.springdataautomappingobjects.domain.dtos.GameDTO;
import bg.softuni.springdataautomappingobjects.domain.entities.Game;
import bg.softuni.springdataautomappingobjects.repositories.GameRepository;
import bg.softuni.springdataautomappingobjects.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static bg.softuni.springdataautomappingobjects.constants.Validations.*;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    GameDTO gameDTO;
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public void addGame(String[] input) {
        if (this.userService.getLoggedInUser() != null
                && this.userService.getLoggedInUser().getAdministrator()) {
            String title = input[1];
            BigDecimal price = new BigDecimal(input[2]);
            float size = Float.parseFloat(input[3]);
            String trailer = input[4];
            String thumbnailUrl = input[5];
            String description = input[6];
            LocalDate releaseDate = LocalDate.now();

            gameDTO = new GameDTO(title, trailer, thumbnailUrl, size,
                    price, description, releaseDate);

            final Game game = this.modelMapper.map(gameDTO, Game.class);
            gameRepository.save(game);
            System.out.printf(ADDED_GAME, title);
        } else {
            throw new IllegalArgumentException(NOT_ADMIN);
        }
    }

    @Override
    public void editGame(String[] input) {
        if (this.userService.getLoggedInUser() != null
                && this.userService.getLoggedInUser().getAdministrator()) {
            int id = Integer.parseInt(input[1]);

            if (this.gameRepository.findAllById(id).isEmpty()) {
                throw new IllegalArgumentException(NO_SUCH_GAME);
            }

            for (int i = 2; i < input.length; i++) {
                String[] underInput = input[i].split("=");
                String command = underInput[0];
                String value = underInput[1];
                switch (command) {
                    case "price" -> this.gameRepository.editGamePrice
                            (new BigDecimal(value), id);
                    case "title" -> this.gameRepository.editGameTitle
                            (value, id);
                    case "size" -> this.gameRepository.editGameSize
                            (Float.parseFloat(value), id);
                    case "trailer" -> this.gameRepository.editGameTrailer
                            (value, id);
                    case "thumbnailURL" -> this.gameRepository.editGameThumbnailURL
                            (value, id);
                    case "description" -> this.gameRepository.editGameDescription
                            (value, id);
                    case "releaseDate" -> this.gameRepository.editGameReleaseDate
                            (LocalDate.parse(value), id);
                    default -> throw new IllegalArgumentException(COMMAND_NOT_FOUND_MESSAGE);
                }
            }
            String title = this.gameRepository.findAllById(id).get().getTitle();
            System.out.printf(EDITED_GAME, title);
        } else {
            throw new IllegalArgumentException(NOT_ADMIN);
        }
    }

    @Override
    public void deleteGame(String[] input) {
        if (this.userService.getLoggedInUser() != null
                && this.userService.getLoggedInUser().getAdministrator()) {
            int id = Integer.parseInt(input[1]);
            if (this.gameRepository.findAllById(id).isEmpty()) {
                throw new IllegalArgumentException(NO_SUCH_GAME);
            }
            String title = this.gameRepository.findAllById(id).get().getTitle();
            this.gameRepository.deleteById(id);
            System.out.printf(DELETED_GAME, title);
        } else {
            throw new IllegalArgumentException(NOT_ADMIN);
        }
    }

    public void showAllGames() {
        if (this.userService.getLoggedInUser() != null) {
            this.gameRepository.findAll()
                    .forEach(g -> System.out.println
                            (g.getTitle() + " " + g.getPrice()));
        } else {
            throw new IllegalArgumentException(NOT_LOGGED);
        }
    }

    public void showAllDetailsForGame(String title) {
        if (this.userService.getLoggedInUser() != null) {
            Optional<Game> game = this.gameRepository.findAllByTitle(title);
            System.out.println(game.toString());
        } else {
            throw new IllegalArgumentException(NOT_LOGGED);
        }
    }

    public void showGamesOwnedByCurrentlyLoggedUser() {
        if (this.userService.getLoggedInUser() != null) {
            Set<Game> games = this.userService.getLoggedInUser().getGames();
            games.forEach(g -> System.out.println(g.getTitle()));
        } else {
            throw new IllegalArgumentException(NOT_LOGGED);
        }
    }
}