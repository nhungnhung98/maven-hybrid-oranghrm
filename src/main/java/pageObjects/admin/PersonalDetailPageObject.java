package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageUIs.PersonalDetailpageUI;

import java.util.List;

public class PersonalDetailPageObject extends BasePage {
    private WebDriver driver;


    public PersonalDetailPageObject(WebDriver driver) {
        super();
    }

    public String getFirsNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailpageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxvalue() {
        waitElementVisible(driver, PersonalDetailpageUI.LAST_NAME_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.LAST_NAME_TEXTBOX,"value");

    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailpageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.EMPLOYEE_ID_TEXTBOX,"value");


    }

}
