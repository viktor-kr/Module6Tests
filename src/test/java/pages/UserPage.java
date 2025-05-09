package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import components.NavigationBar;
import components.PagePopup;
import elements.UiComponentFactory;



public class UserPage {
    private static final Logger logger = LoggerFactory.getLogger(UserPage.class);

    private static final By NAVIGATION_BAR = By.cssSelector("nav[data-testid='navbar']");
    private static final By NAV_BAR_BUTTON = By.cssSelector("li[data-testid='navbar_item']");

    public UserPage() {
        logger.info("Navigate to UserPage");
    }

}