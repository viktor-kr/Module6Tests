package pages.mainPage.models;

import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class ContactTableDto {

    private String name;
    private String city;
    private String phoneNumber;
    private String email;
}