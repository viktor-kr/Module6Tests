import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;


public class TestClass {

private WebDriver driver;
    @Test
    public void loginInSite() throws InterruptedException {
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "normal";
        Configuration.baseUrl = "http://localhost:12301/";
        Configuration.headless = false;

        open(Configuration.baseUrl);
        Thread.sleep(3000);

        $(By.name("username")).click();
        $(By.name("username")).sendKeys("admin");

        $(By.name("password")).click();
        $(By.name("password")).sendKeys("root");

        $(By.name("submit")).click();

        Thread.sleep(4000);

        String successLoginPopup = "div[role='alert']";
        String expectedSuccessLoginPopupText = "You have been logged in successfully.";
        String expectedSuccessLogoutPopupText = "You have been logged out successfully.";

        String actualSuccessLoginPopupText = $(successLoginPopup).shouldHave(Condition.visible).innerText();
        Assert.assertEquals(actualSuccessLoginPopupText, expectedSuccessLoginPopupText);

        String logoutSelector = "li[data-testid='navbar_item']";

        ElementsCollection navigationButtons = $$(By.cssSelector(logoutSelector));
        navigationButtons.get(navigationButtons.size() - 1).click();

        String actualSuccessLogoutPopupText = $(successLoginPopup).shouldHave(Condition.visible).innerText();
        Assert.assertEquals(actualSuccessLogoutPopupText, expectedSuccessLogoutPopupText);

        Thread.sleep(4000);
    }

}
