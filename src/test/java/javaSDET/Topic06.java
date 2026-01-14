package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class Topic06 {
    public abstract void clearStudent();
    // Sẽ có cả hàm non abstract và abstract
    // Không cho phép khởi tạo
    // chỉ cho kế thừa

    //OOP: trừu tượng

    public String getFullname(){
        return null;
    }

    //Action(void)
    public void setFullName(){
        WebDriver driver =new FirefoxDriver();
        driver.findElement(By.cssSelector("")).getText();
    }

}
