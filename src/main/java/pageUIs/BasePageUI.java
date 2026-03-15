package pageUIs;

public class BasePageUI {
    //orangeHRM
    // 9 Locator đại diện cho 9 PageUI
    public static final String SPINNER_ICON = "css=div.oxd-loading-spinner";
    public static final String JOB_LINK= "//a[text()='Job']";
    public static final String PERSONAL_DETAIL_LINK="//a[text()='Personal Details']";
    public static final String DEPENDENT_LINK = "//a[text()='Dependents']";




    //openCart
    public static final String USER_MY_ACCOUNT_HEADER = "XPath=//nav[@id='top']//span[text()='My Account']";
    public static final String USER_LOGOUT_LINK_ITEM = "XPath=//a[@class='dropdown-item' and text()='Logout']";
    public static final String ADMIN_LOGOUT_LINK_ITEM = "XPath=//li[@id='nav-logout']//span[text()='Logout']";
    public static final String USER_HOME_LOGO = "css=div#logo>a";
}
