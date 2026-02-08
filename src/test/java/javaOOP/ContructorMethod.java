package javaOOP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContructorMethod {
    public static void main(String[] args){
         FirefoxDriver ffDriver;

         //Cach 1
         ffDriver =new FirefoxDriver();

         //Cach 2
        FirefoxOptions ffOption =new FirefoxOptions();
        ffOption.addArguments("--headless");
        ffDriver= new FirefoxDriver(ffOption);

        //Cach 3
        WebDriverWait explicitWait;

        explicitWait= new WebDriverWait(ffDriver, Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(ffDriver,Duration.ofSeconds(10),Duration.ofSeconds(1));
    }

    public void secondMethod(String name, int number){
        //Phạm vi trong Block code
        if(name.equals("Automation")){
            int second=15;
            System.out.println(second);
        }

        //System.out.println(second);>> nằm ngoài block code nên k in đc biến second
    }
}
