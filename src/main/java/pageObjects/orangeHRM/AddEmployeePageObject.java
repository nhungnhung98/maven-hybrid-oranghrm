package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.AddEmployeePageUI;
import pageUIs.orangeHRM.BasePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        super();
    }

    public void AddEmployeePageObject(WebDriver driver) {
        this.driver=driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver,AddEmployeePageUI.FIRST_NAME_TEXTBOX,firstName);
    }

    public void enterToLastNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver,AddEmployeePageUI.LAST_NAME_TEXTBOX,firstName);
    }


    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementText(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementClickable(driver,AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver,AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
        return  PageGenerator.getPage(PersonalDetailPageObject.class,driver);
    }
}
