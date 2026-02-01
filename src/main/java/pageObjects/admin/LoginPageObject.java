package pageObjects.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    //Hàm khởi tạo(Constructor method)
    //Map driver từ test class qua Base Object class
    //Sẽ được chạy đầu tiên khi class được gọi tới
    // Nếu không viết hàm khởi tạo thì trình biên dịch sẽ tạo ra cho class này 1 hàm khởi tạo rỗng
    // Nếu viết thì nó sẽ dùng hàm do mình define(User Define)
    // Cùng tên với tên class chứa nó
    //Không có giá trị trả về
    //Có 1 hoặc nhiều tham số/có 1 hoặc nhiều hàm khởi tạo
    // Thể hiện cho tính chất đa hình trong OOP

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver,LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);

    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver,LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);

    }

    public void clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
