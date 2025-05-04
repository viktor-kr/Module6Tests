import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;


public class TestClass {
    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);
    private LoginPage loginPage;

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "root";



    @BeforeClass(alwaysRun = true)

public void precondition(){
    logger.info("=== PREPARE TESTS START ===");

        Configuration.browser = "chrome";
    Configuration.pageLoadStrategy = "normal";
    Configuration.baseUrl = "http://localhost:12301/";
    Configuration.headless = false;
        logger.info("=== PREPARE TESTS FINISH ===");
    open(Configuration.baseUrl);
        logger.info("Open page {}", Configuration.baseUrl);

    }


private WebDriver driver;
    @Test
    public void loginInSite() throws InterruptedException {
        loginPage = new LoginPage();

        String nonLoginPopupMessage = loginPage.getAlertPopupText();
        Assert.assertEquals(nonLoginPopupMessage, LoginPage.NON_LOGIN_MESSAGE);


        MainPage mainPage = loginPage.enterLoginData(ADMIN_LOGIN)
                .enterPasswordData(ADMIN_PASSWORD)
                .pressLogin();

        String successPopupMessage = mainPage.getAlertPopupText();
        String successPopupColor = mainPage.getSuccessPopupColor();
        Assert.assertEquals(successPopupMessage, MainPage.SUCCESS_POPUP_TEXT);
        Assert.assertEquals(successPopupColor, MainPage.SUCCESS_POPUP_COLOR);

        loginPage = mainPage.clickLogoutButton();

        String logoutPopupMessage = loginPage.getAlertPopupText();
        Assert.assertEquals(logoutPopupMessage, LoginPage.LOGOUT_POPUP_TEXT);
    }


}

