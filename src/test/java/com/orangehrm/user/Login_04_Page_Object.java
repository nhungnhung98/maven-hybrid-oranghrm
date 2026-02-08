package com.orangehrm.user;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.*;
import pageUIs.PersonalDetailpageUI;


public class Login_04_Page_Object extends BaseTest {
    private WebDriver driver;
    private String appUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver=getBrowserDriver(appUrl,browserName);

        //Mở URL ra thì nó sẽ mở ra trang Login
        loginPage =new LoginPageObject(driver);

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
        loginPage.clickToLoginButton();

        //Action of Dashboard
        dashboardPage= new DashboardPageObject(driver);
        dashboardPage.clickToPIMModule();

        //Action of Employee List
        employeeListPage = new EmployeeListPageObject(driver);
        employeeListPage.clickToAddEmployeeButton();

        //Action of Add Employee
        addEmployeePage =new AddEmployeePageObject(driver);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        addEmployeePage.getEmployeeID();
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();

        personalDetailPage= new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirsNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxvalue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);


}

    @Test
    public void Employee_02_EditEmployee() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
