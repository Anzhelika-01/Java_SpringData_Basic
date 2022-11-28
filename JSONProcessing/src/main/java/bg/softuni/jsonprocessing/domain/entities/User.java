package bg.softuni.jsonprocessing.domain.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private Integer age;

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    @Fetch(FetchMode.SELECT)
    private Set<Product> boughtProducts;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    @Fetch(FetchMode.JOIN)
    private Set<Product> soldProducts;

    @ManyToMany(cascade = {
            CascadeType.DETACH, CascadeType.MERGE
    })
    @Fetch(FetchMode.JOIN)
    private Set<User> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, getId());
    }
}