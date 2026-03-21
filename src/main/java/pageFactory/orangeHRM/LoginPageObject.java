package pageFactory.orangeHRM;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    //Define các Locator/Element
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameTextbox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button']")
    private String addressTextbox;

    //Bắt buộc các Locator/Element là kiểu dữ liệu WebElement
    //Sự thay đổi về common Class BasePage

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver, usernameTextbox);
        sendkeyToElement(usernameTextbox, username);

    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, passwordTextbox);
        sendkeyToElement(passwordTextbox, password);

    }

    public void clickToLoginButton() {
        waitElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}
