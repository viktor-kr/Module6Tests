package pages.userPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Table;
import pages.userPage.models.UserAction;
import pages.userPage.models.UserTableDto;



public class UserTable extends Table {

    private static final By SELF = By.cssSelector("table[data-testid='users_table']");
    private static final By EMPTY_ROW = By.cssSelector(".dataTables_empty");

    private static final String UPDATE_BUTTON_TEXT = "Update";
    private static final String DELETE_BUTTON_TEXT = "Delete";

    private static final Logger logger = LoggerFactory.getLogger(UserTable.class);

    public UserTable() {
        super(SELF);
    }

    public String getEmptyRowText() {
        return element.find(EMPTY_ROW).getText();
    }

    public UserTableDto getContactByRow(int rowNumber) {
        collectBodyRows();
        ElementsCollection rowsData = collectBodyRowData(rows.get(rowNumber));
        return UserTableDto.builder()
                .name(rowsData.get(0).getText())
                .username(rowsData.get(1).getText())
                .build();
    }

    public UserTableDto getFirstTableUser() {
        return getContactByRow(0);
    }

    public SingleUserPage updateUserByRow(int rowNumber) {
        collectBodyRows();
        ElementsCollection rowsData = collectBodyRowData(rows.get(rowNumber));
        String name = rowsData.get(0).getText();
        String username = rowsData.get(1).getText();
        SelenideElement updateUserButton = rows.get(rowNumber).findAll("a").filter(Condition.text(UserAction.UPDATE.getValue())).first();
        String updateUserButtonText = updateUserButton.getText();
        updateUserButton.click();
        logger.info("User click [{}] for user [{}]", updateUserButtonText, username);
        return new SingleUserPage(name, username);
    }

    public DeleteUserPage deleteUserByRow(int rowNumber) {
        collectBodyRows();
        ElementsCollection rowsData = collectBodyRowData(rows.get(rowNumber));
        String name = rowsData.get(0).getText();
        String username = rowsData.get(1).getText();
        SelenideElement updateUserButton = rows.get(rowNumber).findAll("a").filter(Condition.text(UserAction.DELETE.getValue())).first();
        String updateUserButtonText = updateUserButton.getText();
        updateUserButton.click();
        logger.info("User click [{}] for user [{}]", updateUserButtonText, username);
        return new DeleteUserPage(name, username);
    }

}