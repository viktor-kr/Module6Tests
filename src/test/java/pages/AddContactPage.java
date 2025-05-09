package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Input;
import elements.UiComponentFactory;
import entities.ContactEntity;

import static com.codeborne.selenide.Selenide.$;


public class AddContactPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AddContactPage.class);

    public static final String TITLE_VALUE = "Add Contact";

    public static final By TITLE = By.cssSelector("h2");
    public static final By FORM = By.cssSelector("form");
    public static final By FORM_GROUP = By.cssSelector(".form-group");
    public static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");

    private final Input firstName = UiComponentFactory.createInput(getInputByName("first_name"));
    private final Input middleName = UiComponentFactory.createInput(getInputByName("middle_name"));
    private final Input lastName = UiComponentFactory.createInput(getInputByName("last_name"));

    private static By getInputByName(String name) {
        return By.cssSelector(String.format("input[name='%s']", name));
    }

    public AddContactPage() {
        logger.info("Navigate to AddContactPage");
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(TITLE_VALUE));
    }

    public void fillContactData(ContactEntity contactEntityData) {
        firstName.fillData(contactEntityData.getName());
        middleName.fillData(contactEntityData.getMiddleName());
        lastName.fillData(contactEntityData.getLastName());
    }

}
