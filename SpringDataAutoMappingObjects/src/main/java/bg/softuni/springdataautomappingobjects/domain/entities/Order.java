package bg.softuni.springdataautomappingobjects.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table (name = "orders")
public class Order extends BaseEntity {
    @ManyToOne(optional = false)
    private User user;
    @ManyToMany(targetEntity = Game.class, fetch = FetchType.EAGER)
    private Set<Game> games;

    public Order() {
        this.games = new HashSet<>();
    }
}