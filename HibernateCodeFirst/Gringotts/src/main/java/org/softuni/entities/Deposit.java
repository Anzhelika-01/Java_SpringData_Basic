package org.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Deposit extends BaseEntity{
    @Column(length = 20)
    private String group;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column
    private double amount;

    @Column
    private double interest;

    @Column
    private double charge;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_expired")
    private boolean isExpired;
}