package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;
import pageUIs.BasePageUI;

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
        return  PageGeneratorGeneric.getPage(PersonalDetailPageObject.class,driver);
    }
}
