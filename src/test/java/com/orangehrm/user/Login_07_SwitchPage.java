package com.orangehrm.user;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.*;


public class Login_07_SwitchPage extends BaseTest {
    private WebDriver driver;
    private String appUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailpageObject contactDetailPage;
    private PageGeneratorManager pageGeneratorManager;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver=getBrowserDriver(appUrl,browserName);

        loginPage= PageGeneratorGeneric.getPage(LoginPageObject.class, driver);
        //Mở URL ra thì nó sẽ mở ra trang Login

        adminUsername = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "John";
        employeeLastName = "Terry";

 }

    @Test
    public void Employee_01_CreateNewEmployee() {

        //Action of Login
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage=loginPage.clickToLoginButton();

        //Khoi tao
        //dashboardPage= new DashboardPageObject(driver);

        employeeListPage =dashboardPage.clickToPIMModule();

        //Action of Employee List
        //employeeListPage = new EmployeeListPageObject(driver);
        addEmployeePage=employeeListPage.clickToAddEmployeeButton();

        //Action of Add Employee
        //addEmployeePage =new AddEmployeePageObject(driver);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        addEmployeePage.getEmployeeID();
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage=addEmployeePage.clickToSaveButton();

        //personalDetailPage= new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirsNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxvalue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);

}
    @Test
    public void Employee_02_SwitchPage(){
        //Từ personal qua Contact
        contactDetailPage=personalDetailPage.openContactDetailPage(driver);

        // Từ Contact qua Job
        jobPage=contactDetailPage.openJobPage(driver);

        // Từ Job qua Dependent
        dependentsPage=jobPage.openDependentPage(driver);

        //Từ Dependent qua Personal
        personalDetailPage=dependentsPage.openPersonalDetailPage(driver);

        //Từ Personal qua Job
        personalDetailPage.openJobPage(driver);

        contactDetailPage=jobPage.openContactDetailPage(driver);
        dependentsPage= contactDetailPage.openDependentPage(driver);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
