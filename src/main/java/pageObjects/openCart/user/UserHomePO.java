package pageObjects.openCart.user;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }
}
