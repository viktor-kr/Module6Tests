package entities;
import lombok.Getter;
import tools.DateHelper;

import java.time.LocalDate;


@Getter
public class UserEntity {
    private String fullName;
    private String username;
    private String password;

    public UserEntity withFullName(String data) {
        this.fullName = data;
        return this;
    }

    public UserEntity withUsername(String data) {
        this.username = data;
        return this;
    }

    public UserEntity withPassword(String data) {
        this.password = data;
        return this;
    }
}