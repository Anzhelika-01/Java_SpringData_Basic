package bg.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Patient extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private String address;

    @Column
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column
    private byte[] picture;

    @Column(name = "medical_insurance", nullable = false)
    private boolean medicalInsurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visitation> visitations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Diagnose> diagnoses;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable
    private Set<Medicament> prescriptions;
}