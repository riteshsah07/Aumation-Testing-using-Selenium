package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
}