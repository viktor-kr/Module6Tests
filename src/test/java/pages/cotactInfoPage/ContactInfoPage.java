package pages.cotactInfoPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import elements.Button;
import elements.UiComponentFactory;
import pages.userPage.models.BasePage;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



public class ContactInfoPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ContactInfoPage.class);

    private static final By DATA_ROW = By.cssSelector("div[class='row']");
    private static final By UPDATE_BUTTON =  By.cssSelector(".btn-info");
    private static final By DELETE_BUTTON =  By.cssSelector(".btn-danger");

    private static final String TITLE_VALUE = "%s - View Contact";

    private final Button updateContact = UiComponentFactory.createButton(UPDATE_BUTTON);
    private final Button deleteContact = UiComponentFactory.createButton(DELETE_BUTTON);

    private final ElementsCollection allDataContactRow;
    private final String contactName;

    public ContactInfoPage(String contactName) {
        this.contactName = contactName;
        logger.info("Navigate to ContactInfoPage [{}]", contactName);
        $(TITLE).shouldHave(Condition.visible).shouldHave(Condition.text(String.format(TITLE_VALUE, contactName)));
        allDataContactRow = $$(DATA_ROW);
    }

    public AddEditContactPage navigateToUpdateContact() {
        updateContact.click();
        return new AddEditContactPage(contactName);
    }

    public DeleteContactPage navigateToDeleteContact() {
        deleteContact.click();
        return new DeleteContactPage(contactName);
    }

    public String getValueByTitle(ContactInfoTitle searchTitle) {
        for (int i = 0; i < allDataContactRow.size(); i++) {
            SelenideElement row = allDataContactRow.get(i);
            ElementsCollection rowData = row.findAll("h3");
            if (rowData.get(0).getText().equals(searchTitle.getValue())) {
                return rowData.get(1).getText();
            }
        }
        throw new NoSuchElementException(String.format("ContactInfoPage Data Title [%s] not found", searchTitle.getValue()));
    }


}