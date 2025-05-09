package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import components.PagePopup;
import entities.ContactEntity;
import pages.AddContactPage;
import pages.LoginPage;
import pages.LogsPage;
import pages.MainPage;


public class TestClass extends BaseClass {

    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);

    private ContactEntity contactEntity;

    @BeforeClass
    public void prepareCondition() {
        contactEntity = new ContactEntity();
        contactEntity.withName("123")
                .withMiddleName("345")
                .withLastName("456");
    }

    @Test
    public void testNavBar() throws InterruptedException {
        MainPage mainPage = loginInSite();
        AddContactPage addContactPage = mainPage.goToAddContactPage();
        Thread.sleep(2000);
        addContactPage.fillContactData(contactEntity);
        Thread.sleep(2000);
    }

}