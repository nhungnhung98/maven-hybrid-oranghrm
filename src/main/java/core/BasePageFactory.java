package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {
    private List<WebElement> loadingSpinner;

    public BasePageFactory(){

    }

    public static BasePageFactory getInstance(){
        return new BasePageFactory();
    }
    WebDriver driver;
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(){
        driver.navigate().forward();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until((ExpectedConditions.alertIsPresent()));
    }

    public void acceptToAlert(WebDriver driver){
        //Vừa wait + switch vào Alert
        waitAlertPresence(driver).accept();

    }

    public void cancelToAlert(WebDriver driver){
       waitAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend){
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(int timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle(WebDriver driver, String expectedTitle)  {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void closeAllExcepMain(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }



    //Thao tác với Element
    // PageFactory là define kiểu WebElement

    public void clickToElement( WebElement element){
        element.click();
    }

    public void sendkeyToElement( WebElement element, String keyToSend){
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getElementDOMAttribute(WebDriver driver, WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public String getElementDOMProperty( WebElement element, String propertyName) {
        return element.getDomProperty(propertyName);
    }

    public boolean isElementDisplay(WebDriver driver,WebElement element){
        return element.isDisplayed();
    }
    public boolean isElementSelected(WebDriver driver,WebElement element){
        return element.isSelected();
    }


    public WebElement waitElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementInvisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, List<WebElement> element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, loadingSpinner);
    }



    private static final int SHORT_TIMEOUT=10;
    private static final int LONG_TIMEOUT=30;


}
