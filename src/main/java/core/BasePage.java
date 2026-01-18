package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    //3. Tên hàm: Đặt rên có nghĩa theo chức năng đang cần viết
    //Đặt theo chuẩn lower-camelcase: getPageSource

    //4. Có tham số hay không (tuỳ vào chức năng cần viết)
    // Dùng hàm của Selenium

    //5. Kiểu dữ liệu trả về
    //Dùng hàm của Selenium
    // Nếu có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 2
    // Nếu có return thì n là step cuối cùng


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
        driver.switchTo().alert().accept();
    }

    public void cancelToAlert(WebDriver driver){
        driver.switchTo().alert().dismiss();
    }



}
