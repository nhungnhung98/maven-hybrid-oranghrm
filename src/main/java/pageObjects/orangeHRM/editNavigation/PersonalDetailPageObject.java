package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNaviagation.PersonalDetailpageUI;

public class PersonalDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;


    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
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
