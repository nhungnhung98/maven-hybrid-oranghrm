package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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
import pageObjects.openCart.user.UserRegisterPO;


public class Level09_SwitchUrl extends BaseTest {
    private String userURL, adminURL;

    @Parameters({"browser","adminUrl","userUrl"})
    @BeforeClass
    public void beforeClass(String browserName,String userURL,String adminURL) {
        //Gán dữ liệu
        this.userURL=userURL;
        this.adminURL=adminURL;

        //Mử browser lên là trang user
         driver = getBrowserDriver(userURL, browserName);
         userHomePage= PageGenerator.getPage(UserHomePO.class, driver);

 }

    @Test
    public void OpenCart_01_Logging() {
        userLoginPage=userHomePage.clickToMyAccount();

        userRegisterPage=userHomePage.clickToContinueButton();

        userRegisterPage.enterToFirstName("");
        userRegisterPage.enterToLastName("");
        userRegisterPage.enterToEmail("");
        userRegisterPage.enterToPassWord("");
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        userRegisterPage.clickToLogoutLink();

        userHomePage= userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        adminLoginPage = userHomePage.openAdminSite(driver, adminURL);

        adminLoginPage.enterToUsername("");
        adminLoginPage.enterPassword("");
        adminDashboardPage= adminLoginPage.clickToLoginButton();

        adminCustomerPage= adminDashboardPage.openCustomerPage(driver);
        adminLoginPage= adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        userHomePage=adminLoginPage.openUserSite(driver,userURL);

        adminLoginPage=userLoginPage.openAdminSite(driver,adminURL);

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


}
