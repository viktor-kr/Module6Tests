package pages;

import org.openqa.selenium.By;
import components.NavigationBar;
import components.PagePopup;
import elements.UiComponentFactory;
import pages.mainPage.MainPage;
import pages.userPage.UserPage;


public class BasePage {

    protected static final By TITLE = By.cssSelector("h2");

    protected NavigationBar navigationBar = UiComponentFactory.createNavigationBar(NavigationBar.SELF);
    protected final PagePopup pagePopup = UiComponentFactory.createPagePopup(PagePopup.SELF);

    protected static By getInputByName(String name) {
        return By.cssSelector(String.format("input[name='%s']", name));
    }

    public PagePopup getPagePopup(){
        return pagePopup;
    }

    public LoginPage logout() {
        return navigationBar.clickLogoutButton();
    }

    public MainPage goToAddressBookPage() {
        return navigationBar.clickAddressBookButton();
    }

    public UserPage goToUserPage() {
        return navigationBar.clickUserButton();
    }

    public LogsPage goToLogsPage() {
        return navigationBar.clickLogsButton();
    }

    public ApiPage goToApiPage() {
        return navigationBar.clickApiButton();
    }
}