package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import components.PagePopup;
import elements.UiComponentFactory;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private static final By TITLE = By.cssSelector("h2");
    private static final By INPUT_LOGIN = By.name("username");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[name='submit']");

    private static final String TITLE_TEXT = "Log In";

    public LoginPage() {
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(TITLE_TEXT));
        logger.info("Navigate to LoginPage");
    }

    public String getAlertPopupText() {
        return UiComponentFactory.createPagePopup(PagePopup.SELF)
                .getElement()
                .shouldHave(Condition.visible)
                .getText();
    }

    public LoginPage enterLoginData(String login) {
        UiComponentFactory.createInput(INPUT_LOGIN).fillData(login);
        return this;
    }

    public LoginPage enterPasswordData(String password) {
        UiComponentFactory.createInput(INPUT_PASSWORD).fillData(password);
        return this;
    }

    public MainPage pressLogin() {
        UiComponentFactory.createButton(LOGIN_BUTTON).click();
        return new MainPage();
    }

}