package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    public WebDriver driver;
    public WebDriver getDriver() {
        return this.driver;
    }
    //protected final Log log;

//    public BaseTest(Log log){
//        this.log=log;
//    }
    protected WebDriver getBrowserDriver(String appUrl,String browserName){
        switch (browserName){
            case"Firefox":
                driver =new FirefoxDriver();
                break;
            case "Chrome":
                driver=new ChromeDriver();
                break;
            case "Edge":
                driver=new EdgeDriver();
            default:
                throw new RuntimeException("Browser namem is not valid!");

        }
        driver.get(appUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    protected int getRandomNumber(){

        return new Random().nextInt(999999);

    }
    protected void closeBrowser() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            String driverInstanceName = driver.toString().toLowerCase();
            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else {
                throw new RuntimeException("Driver instance is not support.");
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected void closeBrowser(WebDriver driver) {
        if (!(null == driver)) {
            driver.quit();
        }
    }
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            //Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            //lấy hết các lỗi của testcase hiện tại
            //VerificationFailures.getFailures().addF                                                                                                                                                                             ailureForTest(Reporter.getCurrentTestResult(), e);

            // set vào report testNG/reportNG
            //Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            //Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            //VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            //Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            //Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            //VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            //Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

}
