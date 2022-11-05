package bg.softuni.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail{
    @Id
    @Column(unique = true)
    private int number;

    @ManyToOne
    private User owner;
}