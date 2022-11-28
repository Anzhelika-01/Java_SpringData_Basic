package bg.softuni.springdataautomappingobjects.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

import static bg.softuni.springdataautomappingobjects.constants.Validations.*;

@Getter
@Setter
public class RegistrationDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegistrationDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        setConfirmPassword(confirmPassword);
        this.fullName = fullName;
        validate();
    }

    public void setConfirmPassword(String confirmPassword) {
        if (confirmPassword.equals(password)) {
            this.confirmPassword = confirmPassword;
        } else {
            throw new IllegalArgumentException(PASSWORDS_MISS_MATCH_MESSAGE);
        }
    }

    public void validate(){
        if (!Pattern.matches(PASSWORD_PATTERN, this.password)){
            throw new IllegalArgumentException(USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE);
        }
        if (!Pattern.matches(EMAIL_PATTERN, this.email)){
            throw new IllegalArgumentException(EMAIL_NOT_VALID_MESSAGE);
        }
    }
}