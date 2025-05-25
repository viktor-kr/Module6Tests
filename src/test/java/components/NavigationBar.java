package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import elements.UiComponent;
import pages.*;
import pages.mainPage.MainPage;
import pages.userPage.models.ApiPage;

public class NavigationBar extends UiComponent {

    public static final By SELF = By.cssSelector("nav[data-testid='navbar']");

    public static final By LOGO = By.cssSelector("a[data-testid='navbar_home']");
    public static final By MENU = By.cssSelector("ul[data-testid='navbar_items']");
    public static final By MENU_ITEM = By.cssSelector("li[data-testid='navbar_item']");

    public static final String ADDRESS_BOOK_BUTTON_TEXT = "Address Book";
    public static final String USERS_BUTTON_TEXT = "Users";
    public static final String LOGS_BUTTON_TEXT = "Logs";
    public static final String API_BUTTON_TEXT = "API";
    public static final String LOGOUT_BUTTON_TEXT = "Log Out";
    public static final String LOGO_TEXT = "EvoLoodsen Contacts System";

    public NavigationBar(By selector) {
        super(selector);
    }

    public MainPage clickLogo() {
        element.find(LOGO)
                .shouldHave(Condition.visible)
                .shouldHave(Condition.text(LOGO_TEXT))
                .click();
        return new MainPage();
    }

    public LoginPage clickLogoutButton() {
        getFindButton(LOGOUT_BUTTON_TEXT).click();
        return new LoginPage();
    }

    public MainPage clickAddressBookButton() {
        getFindButton(ADDRESS_BOOK_BUTTON_TEXT).click();
        return new MainPage();
    }

    public UserPage clickUserButton() {
        getFindButton(USERS_BUTTON_TEXT).click();
        return new UserPage();
    }


    public LogsPage clickLogsButton() {
        getFindButton(LOGS_BUTTON_TEXT).click();
        return new LogsPage();
    }

    public ApiPage clickApiButton() {
        getFindButton(API_BUTTON_TEXT).click();
        return new ApiPage();
    }

    private SelenideElement getFindButton(String findButtonText) {
        ElementsCollection allMenuButtons = element.findAll(MENU_ITEM);
        for (int i = 0; i < allMenuButtons.size(); i++) {
            SelenideElement button = allMenuButtons.get(i);
            String currentButtonText = button.find("a").getText();
            if (currentButtonText.equals(findButtonText)) {
                return button;
            }
        }

//        return allMenuButtons.stream()
//                .filter(button -> button.find("a").getText().equals(findButtonText))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Button " + findButtonText + " not find"));

        throw new RuntimeException("Button " + findButtonText + " not find");
    }
}