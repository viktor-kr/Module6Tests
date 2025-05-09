package pages;

import components.NavigationBar;
import elements.UiComponentFactory;


public class BasePage {

    protected NavigationBar navigationBar = UiComponentFactory.createNavigationBar(NavigationBar.SELF);

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }
}