package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;


public class Level09_SwitchUrl_Role extends BaseTest {

    @Parameters({"browser", "adminUrl", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userURL, String adminURL) {
        //Gán dữ liệu
        this.userURL = userURL;
        this.adminURL = adminURL;

        adminUser = "automationfc";
        adminPassword = "Auto@@123";

        userFirstname = "Nhung";
        userLastname = "Nguyen";
        userEmailAddress = "nguyennhung" + getRandomNumber() + "@gmail.com";
        userPassword = "Auto@@123";

        //Mở browser lên là trang user
        driver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);

    }

    @Test(enabled = false)
    public void OpenCart_01_Logging() {
        userLoginPage = userHomePage.clickToMyAccountAtFooter();

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassWord(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userRegisterPage.clickToLogoutLink();

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //User-> Admin
        userHomePage.openAdminSite(driver, adminURL);
        adminLoginPage=PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin-> User
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userLoginPage = userHomePage.clickToMyAccountAtFooter();

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);

        userMyAccountPO = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user -> Admin
        userMyAccountPO.openAdminSite(driver,adminURL);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

    }

    @Test(enabled = false)
    public void OpenCart_02_Without_Logging() {
        //user vào đăng ký tk rồi mua hàng
        //user không logout
        //Chuyển qua trang Admin -> Login 1 lần
        //admin vào verify đơn hàng
        //admin không logout
        //chuyển qua trang user
        //chuyển qua trang admin

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage=PageGenerator.getPage(UserLoginPO.class,driver);


        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassWord(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //User>>Admin
        userRegisterPage.openAdminSite(driver,adminURL);
        adminLoginPage=PageGenerator.getPage(AdminLoginPO.class, driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage=adminDashboardPage.openCustomerPage();

        //Admin >> User
        userHomePage=adminCustomerPage.openUserSite(driver,userURL);
        adminLoginPage=PageGenerator.getPage(AdminLoginPO.class, driver);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPO=PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //User >> Admin
        userMyAccountPO.openAdminSite(driver,userURL);
        adminDashboardPage=PageGenerator.getPage(AdminDashboardPO.class, driver);

    }
    @Test
    public void OpenCart_03_Multiple_Tab() {
        //user vào đăng ký tk rồi mua hàng
        //user không logout
        //Chuyển qua trang Admin -> Login 1 lần
        //admin vào verify đơn hàng
        //admin không logout
        //chuyển qua trang user
        //chuyển qua trang admin

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage=PageGenerator.getPage(UserLoginPO.class,driver);


        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassWord(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        // Khong login nen van o trang Register
        userRegisterPage.openUrlByNewTAB(driver,adminURL);


        //User>>Admin
        userRegisterPage.openAdminSite(driver,adminURL);
        adminLoginPage=PageGenerator.getPage(AdminLoginPO.class, driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage=adminDashboardPage.openCustomerPage();

        //Admin >> User
        adminCustomerPage.switchToWindowByID(driver,userWindowID);

        userRegisterPage= PageGenerator.getPage(UserRegisterPO.class,driver);
        userHomePage =userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyAccountAtFooter();

        userMyAccountPO=PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //User >> Admin
        userMyAccountPO.switchToWindowByID(driver,userWindowID);
        adminCustomerPage=PageGenerator.getPage(AdminCustomerPO.class,driver);


    }

    @AfterClass
    public void afterClass() {
    }

    private WebDriver driver;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserLoginPO userLoginPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private String adminUser, adminPassword;
    private String userURL, adminURL;
    private String userFirstname, userLastname, userEmailAddress, userPassword;
    private UserMyAccountPO userMyAccountPO;
    private String userWindowID,adminWindowID;

}
