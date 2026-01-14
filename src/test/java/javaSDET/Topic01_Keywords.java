package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Topic01_Keywords extends Topic06{

    //Class cùng kiểu class kế thừa >> dùng extends
    // Class kế thừa Interface >> dùng implements
    // Chỉ có non abstract thôi
    //Không có abstract method
    //Khời tạo bình thường
    // CHo phép kế thừa


    //Data Type
    char c='A';
    byte bNumber =10;
    short sNumber=100;
    int iNumber=1400;
    long lNumber=234324;

    float fNumber=15.99F;
    double dNumber=19.39998;

    boolean marialStatus =true;

    String fullName =null;

    //Access Modifier


    //Variable
    private String studentName ="";
    String studentAddress ="";
    protected int studentAge = 30;
    public double studentPoint=9.5;

    //Method
    // Không có kiểu trả về define là void
    private void TC_01(){
        WebDriver driver =new FirefoxDriver();
    }

    void TC_02(){

    }
    protected void TC_03(){

    }
    public void TC_04(){

    }
    @Override
    public void clearStudent(){
        //Tự implement lại ở đây
    }
    //Class/Interface/Enum

}
