package elements;

import org.openqa.selenium.By;

public class PagePopup extends UiComponent{
    public static final By SELF = By.cssSelector("div[role='alert']");
    public PagePopup(By selfSelector) {
        super(selfSelector);
    }
}
