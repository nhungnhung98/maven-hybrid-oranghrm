package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import pageFactory.AddEmployeePageObject;
import pageFactory.DashboardPageObject;
import pageFactory.EmployeeListPageObject;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static pageObjects.admin.DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static AddEmployeePageObject addEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static pageObjects.admin.EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);

    }
    public static PersonalDetailPageObject getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailPageObject(driver);

    }
    public static ContactDetailpageObject getContactDetailpage(WebDriver driver){
        return new ContactDetailpageObject(driver);

    }

}
