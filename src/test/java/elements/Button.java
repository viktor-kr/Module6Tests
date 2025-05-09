package elements;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Button extends UiComponent {

    private static final Logger logger = LoggerFactory.getLogger(Button.class);

    public Button(By selfSelector) {
        super(selfSelector);
    }

    public void click() {
        String buttonText = element.getText();
        element.click();
        logger.info("User click [Button:{}]", buttonText);
    }
}
