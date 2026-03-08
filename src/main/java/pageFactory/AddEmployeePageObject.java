package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.jdk.JdkHttpClient;
import org.openqa.selenium.support.FindBy;
import pageUIs.AddEmployeePageUI;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name()='firstName']")
    private WebElement firstNameTextbox;

    @FindBy(xpath="//input[@name()='lastName']")
    private WebElement lastNameTextbox;

    @FindBy(xpath="//label[text()='Employee Id']/parent::div/following=sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy(xpath="//button[contrains(string(),'Save')]")
    private WebElement saveButon;

    @FindBy(xpath="//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public AddEmployeePageObject(WebDriver driver) {
       this.driver=driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver,firstNameTextbox);
        sendkeyToElement(firstNameTextbox, firstName);

    }
    public String getEmployeeID() {
        waitElementVisible(driver,employeeIDTextbox);
        return getElementDOMProperty( employeeIDTextbox, "value");
    }

    public void clickToSaveButton() {
        waitElementVisible(driver,saveButon);
        clickToElement(saveButon);
    }
    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver,loadingSpinner);
    }
}
