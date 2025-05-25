package pages.cotactInfoPage;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public enum ContactInfoTitle {
    FULL_NAME("Full Name:"),
    HOME_NUMBER("Home Number:"),
    MOBILE_NUMBER("Mobile Number:"),
    EMAIL("Email Address:"),
    BIRTH_DATE("Date Of Birth:"),
    ADDRESS("Address:");

    private final String value;
}