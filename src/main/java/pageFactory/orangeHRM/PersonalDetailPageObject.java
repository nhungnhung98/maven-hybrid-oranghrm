package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(name="firstName")
    private WebElement firstNameTextbox;

    @FindBy(name="lastName")
    private WebElement lastNameTextbox;

    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following=sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy(xpath="//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public PersonalDetailPageObject(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getFirsNameTextboxValue() {
        waitElementVisible(driver, firstNameTextbox);
        return getElementDOMProperty(employeeIDTextbox,"value");
    }

    public String getLastNameTextboxvalue() {
        waitElementVisible(driver, lastNameTextbox);
        return getElementDOMProperty(employeeIDTextbox,"value");
    }

    public String getEmployeeIDTextboxValue() {
        waitElementVisible(driver, employeeIDTextbox);
        return getElementDOMProperty(employeeIDTextbox,"value");
    }
    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver,loadingSpinner);
    }
}
