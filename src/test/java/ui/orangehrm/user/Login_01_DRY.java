package ui.orangehrm.user;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;


public class Login_01_DRY {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


    }

    @Test
    public void Login_01_Empty() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span")).getText(),"Required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']/parent::div/following-sibling::span")).getText(),"Required");
    }

    @Test
    public void Login_02_Invalid_Username() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("nhung@gmail.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='oxd-alert-content oxd-alert-content--error']/p")).getText(),"Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("123456@@");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='oxd-alert-content oxd-alert-content--error']/p")).getText(),"Invalid credentials");

    }
    @Test
    public void Login_04_Valid_Usser_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
        Assert.assertEquals(driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).getText(),"Dashboard");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
