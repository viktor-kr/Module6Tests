package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;



public abstract class UiComponent {

    protected final By selfSelector;
    protected final SelenideElement element;

    protected UiComponent(By selfSelector) {
        this.selfSelector = selfSelector;
        element = $(selfSelector);
    }

    public By getSelfSelector() {
        return selfSelector;
    }

    public SelenideElement getElement() {
        return element;
    }
}