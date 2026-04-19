package com.jquery;

//import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;


public class Level_13_Upload_File extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);

    }

    @Test()
    public void Table_01_Single() {

        homePage.uploadMultipleFiles(driver, mountainFileName);
        homePage.uploadMultipleFiles(driver, riverFileName);
        homePage.uploadMultipleFiles(driver, treeFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFileName));

        homePage.clickStartUpload();
        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFileName));
    }
    @Test()
    public void Table_02_multiple() {
        homePage.refreshToPage(driver);

        homePage.uploadMultipleFiles(driver, mountainFileName, riverFileName, treeFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFileName));

        homePage.clickStartUpload();
        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFileName));
    }


    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;

    String mountainFileName = "Mountain.jpg";
    String riverFileName = "River.jpg";
    String treeFileName = "Tree.jpg";
    String[] fileName = {mountainFileName, riverFileName, treeFileName};
}
