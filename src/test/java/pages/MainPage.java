package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.PagePopup;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    private static final By POPUP = By.cssSelector("div[role='alert']");
    private static final By NAVIGATION_BAR = By.cssSelector("nav[data-testid='navbar']");
    private static final By NAV_BAR_BUTTON = By.cssSelector("li[data-testid='navbar_item']");
    public static final String SUCCESS_POPUP_TEXT = "You have been logged in successfully.";
public static final String SUCCESS_POPUP_COLOR = "linear-gradient(rgb(223, 240, 216) 0px, rgb(200, 229, 188) 100%)";

private PagePopup popup;

public MainPage(){
    logger.info("Navigate to LoginPage");


}

    public String getAlertPopupText(){

    PagePopup pagePopup = new PagePopup(PagePopup.SELF);
    return pagePopup.getElement().shouldHave(Condition.visible).getText();

    }



public LoginPage clickLogoutButton(){
    SelenideElement navBar = $(NAVIGATION_BAR).shouldHave(Condition.visible);
ElementsCollection navBarButtons = navBar.findAll(NAV_BAR_BUTTON);
SelenideElement logoutButton = navBarButtons.get(navBarButtons.size() - 1);
String logoutButtonText = logoutButton.find("a").getText();



logoutButton.click();
    logger.info("User click to [{}]", logoutButtonText);
return new LoginPage();
}


    public String getSuccessPopupColor() {
        //return successPopup.getCssValue("background-image");
   return $(POPUP).shouldHave(Condition.visible).getCssValue("background-image");

    }
}
