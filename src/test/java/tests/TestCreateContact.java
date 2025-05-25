package tests;

import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import BaseTestClass.*;
import components.PagePopup;
import elements.Table;
import entities.ContactEntity;
import pages.cotactInfoPage.AddEditContactPage;
import pages.cotactInfoPage.ContactInfoPage;
import pages.cotactInfoPage.ContactInfoTitle;
import pages.mainPage.MainPage;
import pages.mainPage.models.ContactTableDto;

import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;



public class TestCreateContact extends BaseTestClass {

    private static final Logger logger = LoggerFactory.getLogger(TestCreateContact.class);
    private static final Locale LOCALE = Locale.ENGLISH;

    private final Faker faker = Faker.instance();

    private ContactEntity contactForSave;

    private MainPage mainPage;

    @BeforeClass
    public void prepareCondition() {
        contactForSave = new ContactEntity();
        contactForSave.withName(faker.elderScrolls().firstName())
                .withMiddleName(faker.elderScrolls().race())
                .withLastName(faker.elderScrolls().lastName())
                .withPhoneNumber(String.format("8%s", faker.number().digits(11)))
                .withMobileNumber(String.format("8%s", faker.number().digits(11)))
                .withEmail(String.format("email%s@test.com", faker.number().digits(10)))
                .withBirthDate(LocalDate.of(
                        faker.number().numberBetween(1900, 2025),
                        faker.number().numberBetween(1, 12),
                        faker.number().numberBetween(1, 28)))
                .withAddress1(faker.address().firstName())
                .withAddress2(faker.address().lastName())
                .withCity(faker.address().city())
                .withCountry(faker.address().country())
                .withPostCode(faker.number().digits(6));
    }

    @Test
    public void testCreateContact() {
        mainPage = loginInSite();
        AddEditContactPage addEditContactPage = mainPage.goToAddContactPage();
        mainPage = addEditContactPage.fillContactData(contactForSave).saveContact();

        mainPage.findContact(contactForSave.getEmail());
        ContactTableDto firstContact = mainPage.getContactTable().getFirstTableContact();

        assertThat(firstContact.getName())
                .as("Check Contact FullName")
                .isEqualTo(contactForSave.getFullName());
        assertThat(firstContact.getCity())
                .as("Check Contact City")
                .isEqualTo(contactForSave.getCity());
        assertThat(firstContact.getPhoneNumber())
                .as("Check Contact MobilePhone")
                .isEqualTo(contactForSave.getMobileNumber());
        assertThat(firstContact.getEmail())
                .as("Check Contact Email")
                .isEqualTo(contactForSave.getEmail());

        ContactInfoPage contactInfoPage = mainPage.getContactTable().navigateToFirstTableRowContactPage();
        String actualFullName = contactInfoPage.getValueByTitle(ContactInfoTitle.FULL_NAME);
        String actualHomePhone = contactInfoPage.getValueByTitle(ContactInfoTitle.HOME_NUMBER);
        String actualMobilePhone = contactInfoPage.getValueByTitle(ContactInfoTitle.MOBILE_NUMBER);
        String actualEmail = contactInfoPage.getValueByTitle(ContactInfoTitle.EMAIL);
        String actualBirthDate = contactInfoPage.getValueByTitle(ContactInfoTitle.BIRTH_DATE);
        String actualAddress = contactInfoPage.getValueByTitle(ContactInfoTitle.ADDRESS);
        SoftAssertions.assertSoftly(sa -> {
            sa.assertThat(actualFullName)
                    .as("Check Contact FullName on ContactInfo Page")
                    .isEqualTo(contactForSave.getFullName());
            sa.assertThat(actualHomePhone)
                    .as("Check Contact Home Phone on ContactInfo Page")
                    .isEqualTo(contactForSave.getPhoneNumber());
            sa.assertThat(actualMobilePhone)
                    .as("Check Contact Mobile Phone on ContactInfo Page")
                    .isEqualTo(contactForSave.getMobileNumber());
            sa.assertThat(actualEmail)
                    .as("Check Contact Email on ContactInfo Page")
                    .isEqualTo(contactForSave.getEmail());
            sa.assertThat(actualBirthDate)
                    .as("Check Contact Date on ContactInfo Page")
                    .contains(contactForSave.getFullBirthDate());
            sa.assertThat(actualAddress)
                    .as("Check Contact Home Phone on ContactInfo Page")
                    .isEqualTo(contactForSave.getFullAddress());

            mainPage = contactInfoPage.navigateToDeleteContact().deleteContact();
            String deleteTextPopup = mainPage.getPagePopup().getText();
            assertThat(deleteTextPopup)
                    .as("Check Delete Text Popup")
                    .isEqualTo(PagePopup.DELETE_CONTACT_TEXT);

            mainPage.findContact(contactForSave.getEmail());
            String emptyRowText = mainPage.getContactTable().getEmptyRowText();
            assertThat(emptyRowText)
                    .as("Check Empty Row In Contact Table")
                    .isEqualTo(Table.EMPTY_ROW_TEXT);
        });
    }

    @AfterClass()
    public void afterAction() {
        mainPage.logout();
    }

}
