package bg.softuni.springdataautomappingobjects.services.game;

public interface GameService {
    void addGame(String[] input);

    void editGame(String[] input);

    void deleteGame(String[] input);
    void showAllGames();
    void showAllDetailsForGame(String title);
    void showGamesOwnedByCurrentlyLoggedUser();
}
