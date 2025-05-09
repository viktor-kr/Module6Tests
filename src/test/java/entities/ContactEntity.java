package entities;

import java.time.LocalDate;


public class ContactEntity {
    private String name;
    private String middleName;
    private String lastName;
    private Long phoneNumber;
    private Long mobileNumber;
    private String email;
    private LocalDate birthDate;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postCode;

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

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
}