package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private static final By TITLE = By.cssSelector("h2");
    private static final By INPUT_LOGIN = By.name("username");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By POPUP = By.cssSelector("div[role='alert']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[name='submit']");

    private static final String TITLE_TEXT = "Log In";
    public static final String NON_LOGIN_MESSAGE = "You must be authenticated to view that page. Please log in.";
    public static final String LOGOUT_POPUP_TEXT = "You have been logged out successfully.";


    public LoginPage(){
$(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(TITLE_TEXT));

logger.info("Navigate to LoginPage");
    }

   public String getAlertPopupText(){
       return $(POPUP).shouldHave(Condition.visible).getText();

   }


public LoginPage enterLoginData(String login){
SelenideElement loginInput = $(INPUT_LOGIN);

        loginInput.click();
loginInput.sendKeys(login);
logger.info("Guest click and send data {} in LOGIN INPUT", login);
return this;
    }

    public LoginPage enterPasswordData(String password) {

SelenideElement passInput = $(INPUT_PASSWORD);
        passInput.click();
        passInput.sendKeys(password);
        logger.info("Guest click and send data {} in LOGIN PASSWORD", password);

        return this;
    }

public MainPage pressLogin(){
    SelenideElement loginButton = $(LOGIN_BUTTON);
    String buttonText = loginButton.getText();
    loginButton.click();
    logger.info("Guest click {}", buttonText);

    return new MainPage();
    }

}


