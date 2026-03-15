package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ContactDetailPageUI;
import pageUIs.PersonalDetailpageUI;

public class ContactDetailpageObject extends BasePage {
    private WebDriver driver;


    public ContactDetailpageObject(WebDriver driver) {
        this.driver=driver;
    }


}
