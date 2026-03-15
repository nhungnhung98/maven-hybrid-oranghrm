package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.JobPageUI;
import pageUIs.LoginPageUI;

public class JobPageObject extends BasePage {
    private WebDriver driver;

    public JobPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public DependentsPageObject openDependentPage() {
        waitElementClickable(driver, JobPageUI.DEPENDENT_LINK);
        clickToElement(driver,JobPageUI.DEPENDENT_LINK);

        return  PageGeneratorGeneric.getPage(DependentsPageObject.class, driver);
    }
}
