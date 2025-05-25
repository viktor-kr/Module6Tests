package pages.userPage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAction {
    UPDATE("Update"),
    DELETE("Delete");

    private final String value;
}
