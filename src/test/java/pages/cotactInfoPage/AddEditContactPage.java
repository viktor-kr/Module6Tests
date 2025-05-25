package pages.cotactInfoPage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Button;
import elements.Input;
import elements.UiComponentFactory;
import entities.ContactEntity;
import pages.userPage.models.BasePage;
import pages.mainPage.MainPage;
import tools.DateHelper;

import static com.codeborne.selenide.Selenide.$;



public class AddEditContactPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AddEditContactPage.class);

    public static final String ADD_TITLE_VALUE = "Add Contact";
    public static final String UPDATE_TITLE_VALUE = "%s - Update Contact";

    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");

    private final Input firstName = UiComponentFactory.createInput(getInputByName("first_name"));
    private final Input middleName = UiComponentFactory.createInput(getInputByName("middle_name"));
    private final Input lastName = UiComponentFactory.createInput(getInputByName("last_name"));
    private final Input homePhone = UiComponentFactory.createInput(getInputByName("contact_number_home"));
    private final Input mobilePhone = UiComponentFactory.createInput(getInputByName("contact_number_mobile"));
    private final Input email = UiComponentFactory.createInput(getInputByName("contact_email"));
    private final Input birthDay = UiComponentFactory.createInput(getInputByName("date_of_birth"));
    private final Input address1 = UiComponentFactory.createInput(getInputByName("address_line_1"));
    private final Input address2 = UiComponentFactory.createInput(getInputByName("address_line_2"));
    private final Input city = UiComponentFactory.createInput(getInputByName("address_town"));
    private final Input country = UiComponentFactory.createInput(getInputByName("address_county"));
    private final Input postalCode = UiComponentFactory.createInput(getInputByName("address_post_code"));
    private final Button submitButton = UiComponentFactory.createButton(SUBMIT_BUTTON);


    public AddEditContactPage() {
        logger.info("Navigate to AddContactPage");
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(ADD_TITLE_VALUE));
    }

    public AddEditContactPage(String contactName) {
        logger.info("Navigate to UpdateContactPage");
        String title = String.format(UPDATE_TITLE_VALUE, contactName);
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(title));
    }

    public AddEditContactPage fillContactData(ContactEntity contactEntityData) {
        firstName.fillData(contactEntityData.getName());
        middleName.fillData(contactEntityData.getMiddleName());
        lastName.fillData(contactEntityData.getLastName());
        homePhone.fillData(contactEntityData.getPhoneNumber());
        mobilePhone.fillData(contactEntityData.getMobileNumber());
        email.fillData(contactEntityData.getEmail());
        birthDay.fillData(DateHelper.transformLocalDateToString(contactEntityData.getBirthDate(), "dd.MM.yyyy"));
        address1.fillData(contactEntityData.getAddress1());
        address2.fillData(contactEntityData.getAddress2());
        city.fillData(contactEntityData.getCity());
        country.fillData(contactEntityData.getCountry());
        postalCode.fillData(contactEntityData.getPostCode());
        return this;
    }

    public MainPage saveContact() {
        submitButton.click();
        return new MainPage();
    }

}