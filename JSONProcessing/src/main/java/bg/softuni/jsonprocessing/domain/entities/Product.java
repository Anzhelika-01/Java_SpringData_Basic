package bg.softuni.jsonprocessing.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private User seller;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Category> categories;
}