package elements;

import org.openqa.selenium.By;
import components.NavigationBar;
import components.PagePopup;



public final class UiComponentFactory {

    public static PagePopup createPagePopup(By path) {
        return new PagePopup(path);
    }

    public static Input createInput(By path) {
        return new Input(path);
    }

    public static Button createButton(By path) {
        return new Button(path);
    }

    public static NavigationBar createNavigationBar(By path) {
        return new NavigationBar(path);
    }
}