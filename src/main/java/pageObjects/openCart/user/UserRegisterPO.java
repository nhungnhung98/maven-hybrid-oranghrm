package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirstName(String firstname) {
    waitElementClickable(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
    sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstname);

    }

    public void enterToLastName(String lastname) {
        waitElementClickable(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);

    }

    public void enterToEmail(String email) {
        waitElementClickable(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, email);

    }

    public void enterToPassWord(String password) {
        waitElementClickable(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);

    }

    public void acceptPrivacyCheckbox() {
        waitElementClickable(driver, UserRegisterPageUI.AGREE_CHECKBOX);
        checkToCheckbox(driver,UserRegisterPageUI.AGREE_CHECKBOX);
    }

    public void clickContinueButton() {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver,UserRegisterPageUI.CONTINUE_BUTTON);
    }
    public void clickToLogoutLink() {

    }

    public boolean isSuccessMessageDisplayed() {
        waitElementVisible(driver,UserRegisterPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplay(driver,UserRegisterPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE);
    }
}
