package tests;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import BaseTestClass.*;
import components.PagePopup;
import elements.Table;
import entities.UserEntity;
import pages.userPage.DeleteUserPage;
import pages.userPage.UserPage;
import pages.mainPage.MainPage;
import pages.userPage.models.UserAction;
import pages.userPage.models.UserTableDto;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


public class TestClass extends BaseTestClass {

    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);
    private static final Locale CURRENT_LOCALE = Locale.ENGLISH;

    private final Faker faker = Faker.instance();

    private UserEntity userEntity;

    private MainPage mainPage;

    @BeforeClass
    public void prepareCondition() {
        userEntity = new UserEntity();
        userEntity.withFullName(faker.artist().name())
                .withUsername(String.format("user%s", faker.number().digits(8)))
                .withPassword(String.format("Password%s", faker.number().digits(8)));
    }

    @Test
    public void testCreateContact() {
        mainPage = loginInSite();
        UserPage userPage = mainPage.goToUserPage();
        mainPage = userPage.goToAddUserPage().fillNewUserData(userEntity).saveNewUser();
        String successUserCreateText = mainPage.getPagePopup().getText();
        assertThat(successUserCreateText)
                .as("Check Popup Text Success Create User")
                .isEqualTo(PagePopup.SUCCESS_USER_CREATE);

        mainPage.goToUserPage();
        userPage.findUser(userEntity.getUsername());
        UserTableDto firstUser = userPage.getUserTable().getFirstTableUser();

        assertThat(firstUser.getName())
                .as("Check User Name")
                .isEqualTo(userEntity.getFullName());
        assertThat(firstUser.getUsername())
                .as("Check User UserName")
                .isEqualTo(userEntity.getUsername());

        DeleteUserPage deleteUserPage = userPage.getUserTable().deleteUserByRow(0);
        mainPage = deleteUserPage.deleteContact();
        String successDeleteUserText = mainPage.getPagePopup().getText();
        assertThat(successDeleteUserText)
                .as("Check Popup Text Success Delete User")
                .isEqualTo(PagePopup.SUCCESS_USER_DELETE);

        userPage = mainPage.goToUserPage();
        userPage.findUser(userEntity.getUsername());
        String emptyRowText = userPage.getUserTable().getEmptyRowText();
        assertThat(emptyRowText)
                .as("Check Empty Row In User Table")
                .isEqualTo(Table.EMPTY_ROW_TEXT);
    }

    @AfterClass()
    public void afterAction() {
        mainPage.logout();
    }
}
