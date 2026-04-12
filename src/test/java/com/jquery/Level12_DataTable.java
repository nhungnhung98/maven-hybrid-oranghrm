package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;


public class Level12_DataTable extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {

        //Mở browser lên là trang user
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }

    @Test(enabled = false)
    public void Table_01_Paging() {

        //Mở ra 1 trang bất kì dựa vào số trang truyền vào
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("14");
        Assert.assertTrue(homePage.isPageActiveByNumber("14"));
    }
    @Test (enabled = false)
    public void Table_02_Search() {
        //Search ở bất kì 1 header textbox nào dựa vào tên cột
        homePage.enterToHeaderTextboxByName("Country", "Australia");
        homePage.sleepInSecond(3);

        //Verify bất kì thông tin của 1 country nào
        Assert.assertTrue(homePage.isPageinfoDisplayed("145412","Australia","154696","300109"));
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Females","61394");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isPageinfoDisplayed("61394","Azerbaijan","70542","131942"));
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Males","63391");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isPageinfoDisplayed("60456","Belgium","63391","123847"));
    }

    @Test(enabled = false)
    public void Table_03_Action() {
        homePage.enterToHeaderTextboxByName("Country", "Bahrain");
        homePage.sleepInSecond(3);

        //Có thể xoá/sửa bất kì 1 tên Country nào dựa vào tên Country
        homePage.clickToActionByCountryName("Bahrain","remove");
        homePage.refreshToPage(driver);

        homePage.enterToHeaderTextboxByName("Country", "Argentina");
        homePage.sleepInSecond(3);
        homePage.clickToActionByCountryName("Argentina","edit");

    }
    @Test
    public void Table_04_Index() {
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

    }

    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;

}
