package org.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{
    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "credit_card_number")
    private int creditCardNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;
}