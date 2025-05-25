package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import BaseTestClass.*;
import components.PagePopup;
import pages.LoginPage;
import pages.mainPage.MainPage;


public class TestLoginPage extends BaseTestClass {

    @Test
    public void testLoginAndLogoutSite() {
        LoginPage loginPage = new LoginPage();
        String nonLoginPopupMessage = loginPage.getPagePopup().getText();
        Assert.assertEquals(nonLoginPopupMessage, PagePopup.NON_LOGIN_MESSAGE);

        MainPage mainPage = loginPage.enterLoginData(ADMIN_LOGIN)
                .enterPasswordData(ADMIN_PASSWORD)
                .pressLogin();

        String successPopupMessage = mainPage.getPagePopup().getText();
        String successPopupColor = mainPage.getPagePopup().getBackgroundColor();

        Assert.assertEquals(successPopupMessage, PagePopup.SUCCESS_POPUP_TEXT);
        Assert.assertEquals(successPopupColor, PagePopup.SUCCESS_POPUP_COLOR);

        loginPage = mainPage.logout();

        String logoutPopupMessage = loginPage.getPagePopup().getText();
        Assert.assertEquals(logoutPopupMessage, PagePopup.LOGOUT_POPUP_TEXT);
    }

}
