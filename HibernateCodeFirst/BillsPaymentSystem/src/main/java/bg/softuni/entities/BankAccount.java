package bg.softuni.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetail{
    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "SWIFT_code")
    private String swiftCode;
}
