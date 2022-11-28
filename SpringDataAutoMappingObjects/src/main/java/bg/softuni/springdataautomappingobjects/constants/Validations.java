package bg.softuni.springdataautomappingobjects.constants;

public enum Validations {
    ;
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    public static final String THUMBNAIL_PATTERN = "(http|https)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/[^<\\s]*)?";
    public static final String EMAIL_NOT_VALID_MESSAGE = "Incorrect email.";
    public static final String USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE = "Incorrect username / password";
    public static final String PASSWORDS_MISS_MATCH_MESSAGE = "Passwords are not matching";
    public static final String SUCCESSFULLY_REGISTERED = "%s was registered\n";
    public static final String SOMEONE_ALREADY_LOGGED = "Someone is already logged in";

    public static final String SUCCESSFULLY_LOGGED_OUT = "User %s successfully logged out\n";
    public static final String SUCCESSFULLY_LOGGED_IN = "Successfully logged in ";
    public static final String NO_LOGGED_USER = "Cannot log out. No user was logged in.";
    public static final String EMAIL_ALREADY_EXIST = "An account with this email already exists.";
    public static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found";
    public static final String ADDED_GAME = "Added %s\n";
    public static final String NOT_ADMIN = "You are not an admin";
    public static final String NOT_LOGGED = "You are not logged";
    public static final String NO_SUCH_GAME = "No such game exist";
    public static final String EDITED_GAME = "Edited %s\n";
    public static final String DELETED_GAME = "Deleted %s\n";
    public static final String INVALID_GAME_TITLE = "Title has to begin with an uppercase letter and must " +
            "have length between 3 and 100 symbols (inclusively)";
    public static final String INVALID_GAME_PRICE = "Price must be a positive number";
    public static final String INVALID_GAME_SIZE = "Size must be a positive number";
    public static final String INVALID_GAME_THUMBNAIL = "The thumbnail" +
            " should be a plain text starting with http://, https://";
    public static final String INVALID_GAME_DESCRIPTION =
            "The description must be at least 20 symbols";
}