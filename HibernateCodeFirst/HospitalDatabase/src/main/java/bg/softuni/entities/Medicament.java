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
public class Medicament extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<Patient> patients;
}