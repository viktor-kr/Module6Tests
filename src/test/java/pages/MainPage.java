package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import components.PagePopup;
import elements.Button;
import elements.UiComponentFactory;



public class MainPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

    public static final By ADD_CONTACT_BUTTON = By.cssSelector("a[data-testid='add_contact']");

    private final Button addContactButton = UiComponentFactory.createButton(ADD_CONTACT_BUTTON);


    public MainPage() {
        logger.info("Navigate to MainPage");
    }

    public String getAlertPopupText() {
        return UiComponentFactory.createPagePopup(PagePopup.SELF)
                .getElement()
                .shouldHave(Condition.visible).getText();
    }

    public LoginPage logout() {
        return navigationBar.clickLogoutButton();
    }

    public AddContactPage goToAddContactPage() {
        addContactButton.click();
        return new AddContactPage();
    }

    public String getSuccessPopupColor() {
        return UiComponentFactory.createPagePopup(PagePopup.SELF)
                .getElement().shouldHave(Condition.visible)
                .getCssValue("background-image");
    }
}
