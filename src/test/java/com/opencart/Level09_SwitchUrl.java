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


public class Level09_SwitchUrl extends BaseTest {

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

    @Test
    public void OpenCart_01_Logging() {
        userLoginPage = userHomePage.clickToMyAccount();

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
        adminLoginPage = userHomePage.openAdminSite(driver, adminURL);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage(driver);
        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin-> User
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userLoginPage = userHomePage.clickToMyAccount();

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);

        userMyAccountPO = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDisplayed());

        //user -> Admin
        adminLoginPage=userMyAccountPO.openAdminSite(driver,adminURL);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

    }

    @Test
    public void OpenCart_02_Without_Logging() {

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


}
