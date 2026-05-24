package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNaviagation.PersonalDetailpageUI;

public class PersonalDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;


    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Get Firstname textbox attribute value")
    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailpageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.FIRST_NAME_TEXTBOX,"value");
    }
    @Step("Get Lastname textbox attribute value")
    public String getLastNameTextboxValue() {
        waitElementVisible(driver, PersonalDetailpageUI.LAST_NAME_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.LAST_NAME_TEXTBOX,"value");

    }
    @Step("Get EmployeeID textbox attribute value")
    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, PersonalDetailpageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMAttribute(driver, PersonalDetailpageUI.EMPLOYEE_ID_TEXTBOX,"value");


    }


}
