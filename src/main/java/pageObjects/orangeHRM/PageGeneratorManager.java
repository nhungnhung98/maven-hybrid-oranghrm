package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageFactory.orangeHRM.AddEmployeePageObject;
import pageFactory.orangeHRM.DashboardPageObject;
import pageFactory.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static pageObjects.orangeHRM.DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static AddEmployeePageObject addEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static pageObjects.orangeHRM.EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);

    }
    public static PersonalDetailPageObject getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailPageObject(driver);

    }
    public static ContactDetailPageObject getContactDetailpage(WebDriver driver){
        return new ContactDetailPageObject(driver);

    }

}
