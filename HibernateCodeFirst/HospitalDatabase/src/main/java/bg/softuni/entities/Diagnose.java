package bg.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Diagnose extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;
}