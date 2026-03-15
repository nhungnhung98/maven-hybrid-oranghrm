package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DependentsPageUI;
import pageUIs.JobPageUI;

public class DependentsPageObject extends BasePage {
    WebDriver driver;

    public DependentsPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementClickable(driver, DependentsPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, DependentsPageUI.PERSONAL_DETAIL_LINK);

        return PageGeneratorGeneric.getPage(PersonalDetailPageObject.class,driver);

    }
}
