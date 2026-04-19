package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageUIs.BasePageUI;
import pageUIs.jquery.HomePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // 1. Access Modifier
    //public:Tất cả các class trong cùng/khác packeage đều sử dụng được các hàm này
    //Chỉ muốn cho class nào kế thừa mới dùng thôi => Không dùng public
    //protected: Chỉ class nào kế thừa mới dùng được
    //private: Chỉ cho các hàm trong cùng class này sử dụng
    //default: Chỉ cho các class trong cùng package sử dụng
    //Các class trong pageObject không dùng được=> Không dùng default

    // 2. Kiểu dữ liệu: void/int/String/boolean/WebElement
    //Nó sẽ liên quan đến cái chức năng mình viết thân hàm
    // Dùng hàm nào của Selenium thì n trả về gì>> Define kiểu dữ liệu của hàm tương ứng

    //3. Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    //Đặt theo chuẩn lower-camelcase: getPageSource

    //4. Có tham số hay không (tuỳ vào chức năng cần viết)
    // Dùng hàm của Selenium

    //5. Kiểu dữ liệu trả về
    //Dùng hàm của Selenium
    // Nếu có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 2
    // Nếu có return thì n là step cuối cùng

    //hàm static có nhiệm vụ lấy ra instance của chính class này
    //Một biến static/hàm static có thể gọi ra trực tiếp tu pham vi class
    public static BasePage getInstance(){
        return new BasePage();
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


    public WebElement getWebElement(WebDriver driver, String locator){
       return driver.findElement(getByLocator(locator));
    }

    private By getByXPath(String locator) {
        return By.xpath(locator);
    }

    private String castParameter(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    private By getByLocator(String locatorType) {
        System.out.println("Locator type =" + locatorType);

        if (locatorType == null || locatorType.trim().isEmpty()) {
            throw new IllegalArgumentException("Local type cannot be null or empty.");
        }

        String[] locatorArr = locatorType.split("=", 2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        switch (locatorPrefix.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type is not supported" + locatorType);
        }
    }


    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).click();}

    public void sendkeyToElement(WebDriver driver, String locator, String keyToSend){
        getWebElement(driver,locator).sendKeys(keyToSend);
    }
    public void sendkeyToElement(WebDriver driver, String locator, CharSequence keyToSend, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).clear();
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(keyToSend);
    }

    private List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }


    protected WebElement getWebElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElement(getByLocator(castParameter(locator, restValue)));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElements(getByLocator(castParameter(locator, restValue)));
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String valueItem, String... restValue) {
        new Select(getWebElement(driver, castParameter(locator, restValue))).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDromdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem, String... restValue) {
        clickToElement(driver, castParameter(parentLocator, restValue));
        sleepInSecond(1);

        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }


    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getText();
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public void getHexaByRGBA(String rgbaColor){
        Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getElementsNumber(WebDriver driver,String locator){
        return getListElement(driver,locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String... restValue) {
        return getListElement(driver, castParameter(locator, restValue)).size();
    }

    public void checkToCheckbox(WebDriver driver,String locator){
        if (!isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }
    public void checkToCheckbox(WebDriver driver, String locator, String... restValue) {
        if (!isElementSelected(driver, castParameter(locator, restValue))) {
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver,String locator){
        if (isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplay(WebDriver driver,String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver,String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).isSelected();
    }

    public void switchToFrame(WebDriver driver,String locator){
        driver.switchTo().frame(getWebElement(driver,locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourcelocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourcelocator),getWebElement(driver,sourcelocator)).perform();
    }

    public void sendKetBoardToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDOMAttribute(driver, locator, "style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restValue))));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }
    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitElementClickable(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restValue))));
    }


    public void waitElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver,locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalContants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
            return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator, String... restValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(locator, restValue))));
    }

    //OpenCart
    public UserHomePO clickToLogoutLinkAtUserSite(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);

        waitElementClickable(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }
    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);

        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public void openAdminSite(WebDriver driver,String adminURL) {
        openPageUrl(driver,adminURL);

    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public UserHomePO openUserSite(WebDriver driver,String userURL) {
        openPageUrl(driver,userURL);
        return PageGenerator.getPage(UserHomePO.class, driver);

    }

    public void openUrlByNewTAB(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }
    public void openUrlByNewWindow(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
    }

    public String getCurrentWindowID(WebDriver driver){
        return driver.getWindowHandle();
    }

    public UserHomePO openHomeLogo(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver,BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }
    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public boolean isFileLoadedSuccess(String fileName) {
        waitElementVisible(driver, HomePageUI.IS_FILE_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.IS_FILE_LOADED, fileName);
    }

    public void clickStartUpload() {
        List<WebElement> startButtons = getListElement(driver, HomePageUI.START_UPLOAD_BUTTON);
        for (WebElement startButton:startButtons){
            waitElementClickable(driver, startButton).click();
            sleepInSecond(2);
        }
    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitElementVisible(driver, HomePageUI.IS_FILE_UPLOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.IS_FILE_UPLOADED, fileName);
    }

    private static final int SHORT_TIMEOUT=10;
    private static final int LONG_TIMEOUT=30;


}
