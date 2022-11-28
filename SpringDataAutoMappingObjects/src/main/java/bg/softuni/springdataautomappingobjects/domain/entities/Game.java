package bg.softuni.springdataautomappingobjects.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column
    private String trailer;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Column(nullable = false)
    private float size;

    @Column
    private BigDecimal price;

    @Column
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Game(String title, String trailer,
                String imageThumbnail, float size, BigDecimal price,
                String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Title: " + title + '\'' +
                "Trailer: '" + trailer + '\'' +
                "ImageThumbnail:'" + imageThumbnail + '\'' +
                "Size: " + size +
                "Price: " + price +
                "Description: " + description + '\'' +
                "ReleaseDate: " + releaseDate;
    }
}