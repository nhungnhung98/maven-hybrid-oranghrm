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
    private String employeeID;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver=getBrowserDriver(appUrl,browserName);
        loginPage =new LoginPageObject();

 }

    @Test
    public void Employee_01_CreateNewEmployee() {

        //Action of Login
        loginPage.enterToUsernameTextbox("Admin");
        loginPage.enterToPasswordTextbox("Admin");
        loginPage.clickToLoginButton();

        //Action of Dashboard
        dashboardPage= new DashboardPageObject();
        dashboardPage.clickToPIMModule();

        //Action of Employee List
        employeeListPage = new EmployeeListPageObject();
        employeeListPage.clickToAddEmployeeButton();

        //Action of Add Employee
        addEmployeePage =new AddEmployeePageObject();
        addEmployeePage.enterToFirstNameTextbox();
        addEmployeePage.enterToLastNameTextbox();
        addEmployeePage.getEmployeeID();
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();

        personalDetailPage= new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirsNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxvalue(),"");
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
