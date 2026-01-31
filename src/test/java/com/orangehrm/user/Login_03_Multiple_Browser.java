package com.orangehrm.user;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;


public class Login_03_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private String appUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private BasePage basePage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        basePage =BasePage.getInstance();
        driver=getBrowserDriver(appUrl,browserName);

 }

    @Test
    public void Login_01_Empty() {
        basePage.openPageUrl(driver, appUrl);
        driver.get(appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","");
        basePage.sendkeyToElement(driver,"//input[@name='password']","");

        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");

}

    @Test
    public void Login_02_Invalid_Username() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendkeyToElement(driver, "//input[@name='username']", "nhung@gmail.com");
        basePage.sendkeyToElement(driver, "//input[@name='password']", "123456");
        basePage.clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"), "Invalid credentials");

    }
    @Test
    public void Login_03_Invalid_Password() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendkeyToElement(driver, "//input[@name='username']", "Admin");
        basePage.sendkeyToElement(driver, "//input[@name='password']", "123456@");
        basePage.clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"), "Invalid credentials");

    }
    @Test
    public void Login_04_Valid_User_Password(){
        basePage.openPageUrl(driver,appUrl);

        basePage.sendkeyToElement(driver,"//input[@name='username']","Admin");
        basePage.sendkeyToElement(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSprinnerInvisible());
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='oxd-topbar-header-title'//h6"),"Dashboard");
    }

    public boolean isAllLoadingSprinnerInvisible(){
        return basePage.waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']"); }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
