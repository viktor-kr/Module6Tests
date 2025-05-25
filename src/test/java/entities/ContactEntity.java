package entities;


import lombok.Getter;
import tools.DateHelper;

import java.time.LocalDate;


@Getter
public class ContactEntity {
    private String name;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String mobileNumber;
    private String email;
    private LocalDate birthDate;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postCode;

    public ContactEntity withPostCode(String data) {
        this.postCode = data;
        return this;
    }

    public ContactEntity withCountry(String data) {
        this.country = data;
        return this;
    }

    public ContactEntity withCity(String data) {
        this.city = data;
        return this;
    }

    public ContactEntity withAddress2(String data) {
        this.address2 = data;
        return this;
    }

    public ContactEntity withAddress1(String data) {
        this.address1 = data;
        return this;
    }

    public ContactEntity withBirthDate(LocalDate data) {
        this.birthDate = data;
        return this;
    }

    public ContactEntity withEmail(String data) {
        this.email = data;
        return this;
    }

    public ContactEntity withPhoneNumber(String data) {
        this.phoneNumber = data;
        return this;
    }

    public ContactEntity withMobileNumber(String data) {
        this.mobileNumber = data;
        return this;
    }

    public ContactEntity withName(String data) {
        this.name = data;
        return this;
    }

    public ContactEntity withMiddleName(String data) {
        this.middleName = data;
        return this;
    }

    public ContactEntity withLastName(String data) {
        this.lastName = data;
        return this;
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s, %s",
                address1, address2, city, country, postCode);
    }

    public String getMonthYearBirthDate() {
        return DateHelper.transformLocalDateToString(birthDate, DateHelper.MONTH_YEAR_DATE_PATTERN);
    }

    public String getFullBirthDate() {
        int day = birthDate.getDayOfMonth();
        String suffix;
        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            suffix = switch (day % 10) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }
        return String.format("%s%s %s", day, suffix, DateHelper.transformLocalDateToString(birthDate, DateHelper.MONTH_YEAR_DATE_PATTERN));
    }

    public String getFullName() {
        return String.format("%s %s %s", name, middleName, lastName);
    }
}