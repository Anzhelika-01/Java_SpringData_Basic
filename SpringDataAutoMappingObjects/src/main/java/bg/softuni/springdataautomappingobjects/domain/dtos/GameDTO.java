package bg.softuni.springdataautomappingobjects.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

import static bg.softuni.springdataautomappingobjects.constants.Validations.*;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {
    private String title;
    private String trailer;
    private String imageThumbnail;
    private float size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDTO(String title, String trailer, String imageThumbnail,
                   float size, BigDecimal price, String description,
                   LocalDate releaseDate) {
        this.title = title;
        setTrailer(trailer);
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        validate();
    }
    private void validate(){
        if (title != null &&
                Character.isLowerCase(title.charAt(0))
                || title.length() <= 3
                || title.length() >= 100) {
            throw new IllegalArgumentException(INVALID_GAME_TITLE);
        }

        if (price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException(INVALID_GAME_PRICE);
        }

        if (size < 0){
            throw new IllegalArgumentException(INVALID_GAME_SIZE);
        }

        if (!Pattern.matches(THUMBNAIL_PATTERN, this.imageThumbnail)){
            throw new IllegalArgumentException(INVALID_GAME_THUMBNAIL);
        }

        if (description.length() < 20){
            throw new IllegalArgumentException(INVALID_GAME_DESCRIPTION);
        }
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer.substring(trailer.length() - 11);
    }
}