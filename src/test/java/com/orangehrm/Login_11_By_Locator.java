package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;


public class Login_11_By_Locator extends BaseTest {
    private WebDriver driver;
    private String appUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private PageGeneratorManager pageGeneratorManager;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver=getBrowserDriver(appUrl,browserName);

        loginPage= PageGenerator.getPage(LoginPageObject.class, driver);
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
    public void Employee_02_Dynamic_Page(){
         // Từ Personal qua Contact
        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorPageByName("Contact Details");

        // Từ Contact qua Job
        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorPageByName("Job");

        // Từ Job qua Dependent
        dependentsPage = (DependentsPageObject) jobPage.openEditNavigatorPageByName("Dependents");

        // Từ Dependent qua Personal
        personalDetailPage = (PersonalDetailPageObject) dependentsPage.openEditNavigatorPageByName("Personal Details");

        // Từ Personal qua Job
        jobPage = (JobPageObject) personalDetailPage.openEditNavigatorPageByName("Job");

        contactDetailPage = (ContactDetailPageObject) jobPage.openEditNavigatorPageByName("Contact Details");

        dependentsPage = (DependentsPageObject) contactDetailPage.openEditNavigatorPageByName("Dependents");
    }
    @Test
    public void Employee_03_Dynamic_Page() {
        // Từ Personal qua Contact
        personalDetailPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        // Từ Contact qua Job
        contactDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        // Từ Job qua Dependent
        jobPage.openEditNavigatorByName("Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);

        // Từ Dependent qua Personal
        dependentsPage.openEditNavigatorByName("Personal Details");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        // Từ Personal qua Job
        personalDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        jobPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        contactDetailPage.openEditNavigatorByName("Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
