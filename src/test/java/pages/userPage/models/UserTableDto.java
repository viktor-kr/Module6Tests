package pages.userPage.models;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserTableDto {

    private String name;
    private String username;
}
