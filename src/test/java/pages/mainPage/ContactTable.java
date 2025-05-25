package pages.mainPage;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Table;
import pages.cotactInfoPage.ContactInfoPage;
import pages.mainPage.models.ContactTableDto;



public class ContactTable extends Table {

    private static final By SELF = By.cssSelector("table[data-testid='contacts_table']");
    private static final By EMPTY_ROW = By.cssSelector(".dataTables_empty");

    private static final String VIEW_BUTTON_TEXT = "View";

    private static final Logger logger = LoggerFactory.getLogger(ContactTable.class);

    public ContactTable() {
        super(SELF);
    }

    public String getEmptyRowText() {
        return element.find(EMPTY_ROW).getText();
    }

    public ContactTableDto getContactByRow(int rowNumber) {
        collectBodyRows();
        ElementsCollection rowsData = collectBodyRowData(rows.get(rowNumber));
        return ContactTableDto.builder()
                .name(rowsData.get(0).getText())
                .city(rowsData.get(1).getText())
                .phoneNumber(rowsData.get(2).getText())
                .email(rowsData.get(3).getText())
                .build();
    }

    public ContactTableDto getFirstTableContact() {
        return getContactByRow(0);
    }

    public ContactInfoPage navigateToContactPageByRow(int rowNumber) {
        collectBodyRows();
        ElementsCollection rowsData = collectBodyRowData(rows.get(rowNumber));
        String contactName = rowsData.get(0).getText();
        SelenideElement viewContactButton = rows.get(rowNumber).findAll("a").filter(Condition.text(VIEW_BUTTON_TEXT)).first();
        String viewContactButtonText = viewContactButton.getText();
        viewContactButton.click();
        logger.info("User click [{}] for contact [{}]", viewContactButtonText, contactName);
        return new ContactInfoPage(contactName);
    }

    public ContactInfoPage navigateToFirstTableRowContactPage() {
        return navigateToContactPageByRow(0);
    }

}