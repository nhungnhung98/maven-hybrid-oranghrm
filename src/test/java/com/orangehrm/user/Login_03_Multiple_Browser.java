package com.orangehrm.user;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Login_03_Multiple_Browser extends BasePage {
    private WebDriver driver;
    private String appUrl= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Login_01_Empty() {
      openPageUrl(driver, appUrl);
        driver.get(appUrl);

        sendkeyToElement(driver,"//input[@name='username']","");
        sendkeyToElement(driver,"//input[@name='password']","");

        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");

}

    @Test
    public void Login_02_Invalid_Username() {
       openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "nhung@gmail.com");
        sendkeyToElement(driver, "//input[@name='password']", "123456");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"), "Invalid credentials");

    }
    @Test
    public void Login_03_Invalid_Password() {
        openPageUrl(driver, appUrl);

        sendkeyToElement(driver, "//input[@name='username']", "Admin");
        sendkeyToElement(driver, "//input[@name='password']", "123456@");
        clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver, "//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"), "Invalid credentials");

    }
    @Test
    public void Login_04_Valid_User_Password(){
        openPageUrl(driver,appUrl);

        sendkeyToElement(driver,"//input[@name='username']","Admin");
        sendkeyToElement(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSprinnerInvisible());
        Assert.assertEquals(getElementText(driver,"//div[@class='oxd-topbar-header-title'//h6"),"Dashboard");
    }

    public boolean isAllLoadingSprinnerInvisible(){
        return waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']"); }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
