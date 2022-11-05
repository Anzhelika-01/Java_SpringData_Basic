package bg.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Visitation extends BaseEntity {
    @Column(nullable = false)
    private Date date;

    @Column
    private String comments;

    @ManyToOne(optional = false)
    private Patient patient;
}