package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class Topic08 {
    @Test
    public void TC_01() throws InterruptedException {
        String osName ="Windows 11";
        String separator=null;
        //Condition Statement
        //If else
        if(osName.contains("Windows")){
            separator="\\";
        }else{
            separator="/";
        }

        String browserName = "Chrome";
        WebDriver driver;
        //switch-case
        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break;

            case "FireFox":
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Browser name is not supported");
        }

        //Loop Statement

        int studentNumber=10;
        //Classic for
        //for
        for(int i=0; i< studentNumber; i++){
            System.out.println(i);
        }
        for(int i=0; i< studentNumber; i++) {
            if (i == 5) {
                System.out.println(i);
            }
        }

        //for-each
        //while
        //do -while
        int z=10;
        do {
            System.out.println(z);
            z++;
        }while (z<studentNumber);

        //Nếu element k được tìm thấy cũng k bị lỗi
        try {
            //Happy case
            driver.findElement(By.cssSelector("")).isDisplayed();
        }catch (NoSuchElementException exception){
            //Egde Case
            System.out.println(exception.getMessage());
        }finally {
            //Close Connection : DB//file
        }
    }

}
