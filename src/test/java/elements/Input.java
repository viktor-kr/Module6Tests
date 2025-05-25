package elements;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Input extends UiComponent {

    private static final Logger logger = LoggerFactory.getLogger(Input.class);

    public Input(By selfSelector) {
        super(selfSelector);
    }

    public void fillData(String data) {
        element.click();
        String inputName = element.getAttribute("placeholder");
        element.sendKeys(data);
        logger.info("User click [Input:{}] and send data [{}]", inputName, data);
    }

    public void clickCheckBox() {
        element.click();
        logger.info("User click for Confirm Checkbox");
    }
}