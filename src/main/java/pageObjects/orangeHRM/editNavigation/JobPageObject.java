package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNaviagation.JobPageUI;

public class JobPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public JobPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

//    public DependentsPageObject openDependentPage() {
//        waitElementClickable(driver, JobPageUI.DEPENDENT_LINK);
//        clickToElement(driver,JobPageUI.DEPENDENT_LINK);
//
//        return  PageGenerator.getPage(DependentsPageObject.class, driver);
//    }
}
