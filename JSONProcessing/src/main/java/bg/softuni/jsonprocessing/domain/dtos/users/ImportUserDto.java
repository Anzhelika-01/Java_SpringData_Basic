package bg.softuni.jsonprocessing.domain.dtos.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImportUserDto {
    private String firstName;
    private String lastName;
    private int age;

    public ImportUserDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        validate();
    }

    private void validate() {
        if (lastName.length() < 3) {
            throw new IllegalArgumentException
                    ("The last name should be at least 3 characters.");
        }
    }
}