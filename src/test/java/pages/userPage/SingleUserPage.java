package pages.userPage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Button;
import elements.Input;
import elements.UiComponentFactory;
import entities.UserEntity;
import pages.BasePage;
import pages.mainPage.MainPage;

import static com.codeborne.selenide.Selenide.$;



public class SingleUserPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SingleUserPage.class);

    public static final String ADD_TITLE_VALUE = "Add User";
    public static final String UPDATE_TITLE_VALUE = "%s [%s] - Update User";

    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    private static final By UPDATE_NAME_SUBMIT_BUTTON = By.cssSelector("button[type='submit_name']");
    private static final By UPDATE_PASSWORD_SUBMIT_BUTTON = By.cssSelector("button[type='submit_password']");

    private final Input fullName = UiComponentFactory.createInput(getInputByName("full_name"));
    private final Input username = UiComponentFactory.createInput(getInputByName("username"));
    private final Input password = UiComponentFactory.createInput(getInputByName("password"));
    private final Input confirmPassword = UiComponentFactory.createInput(getInputByName("confirm_password"));
    private final Button updateUserName = UiComponentFactory.createButton(UPDATE_NAME_SUBMIT_BUTTON);
    private final Button updatePassword = UiComponentFactory.createButton(UPDATE_PASSWORD_SUBMIT_BUTTON);
    private final Button saveNewUser = UiComponentFactory.createButton(SUBMIT_BUTTON);

    private final boolean isUpdateUserState;

    public SingleUserPage() {
        logger.info("Navigate to Add UserPage");
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(ADD_TITLE_VALUE));
        isUpdateUserState = false;
    }

    public SingleUserPage(String name, String username) {
        String title = String.format(UPDATE_TITLE_VALUE, name, username);
        logger.info("Navigate to Update UserPage");
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(title));
        isUpdateUserState = true;
    }

    public SingleUserPage fillNewUserData(UserEntity userEntity) {
        fullName.fillData(userEntity.getFullName());
        username.fillData(userEntity.getUsername());
        password.fillData(userEntity.getPassword());
        confirmPassword.fillData(userEntity.getPassword());
        return this;
    }

    public SingleUserPage editUserName(UserEntity userEntity) {
        if (!isUpdateUserState) {
            throw new InvalidElementStateException("Wrong Page State: Add New User");
        }
        fullName.fillData(userEntity.getFullName());
        username.fillData(userEntity.getUsername());
        return this;
    }

    public SingleUserPage editUserPassword(UserEntity userEntity) {
        if (!isUpdateUserState) {
            throw new InvalidElementStateException("Wrong Page State: Add New User");
        }
        password.fillData(userEntity.getPassword());
        confirmPassword.fillData(userEntity.getPassword());
        return this;
    }

    public UserPage confirmChangeUserName() {
        updateUserName.click();
        return new UserPage();
    }

    public UserPage confirmChangeUserPassword() {
        updatePassword.click();
        return new UserPage();
    }

    public MainPage saveNewUser() {
        saveNewUser.click();
        return new MainPage();
    }



}
