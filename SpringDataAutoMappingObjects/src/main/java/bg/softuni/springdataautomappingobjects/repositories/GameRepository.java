package bg.softuni.springdataautomappingobjects.repositories;

import bg.softuni.springdataautomappingobjects.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findAllById(Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.price = :price WHERE g.id = :id")
    void editGamePrice(BigDecimal price, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.size = :size WHERE g.id = :id")
    void editGameSize(float size, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.title = :title WHERE g.id = :id")
    void editGameTitle(String title, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.description = :description WHERE g.id = :id")
    void editGameDescription(String description, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.releaseDate = :releaseDate WHERE g.id = :id")
    void editGameReleaseDate(LocalDate releaseDate, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.trailer = :trailer WHERE g.id = :id")
    void editGameTrailer(String trailer, int id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.imageThumbnail = :imageThumbnail WHERE g.id = :id")
    void editGameThumbnailURL(String imageThumbnail, int id);

    @Transactional
    void deleteById(int id);

    Optional<Game> findAllByTitle(String title);

    List<Game> findAll();
}