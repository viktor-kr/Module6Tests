package components;

import org.openqa.selenium.By;
import elements.UiComponent;
public class PagePopup extends UiComponent {

    public static final String SUCCESS_POPUP_TEXT = "You have been logged in successfully.";
    public static final String SUCCESS_POPUP_COLOR = "linear-gradient(rgb(223, 240, 216) 0px, rgb(200, 229, 188) 100%)";
    public static final String NON_LOGIN_MESSAGE = "You must be authenticated to view that page. Please log in.";
    public static final String LOGOUT_POPUP_TEXT = "You have been logged out successfully.";

    public static final By SELF = By.cssSelector("div[role='alert']");

    public PagePopup(By selfSelector) {
        super(selfSelector);
    }
}