package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import pages.CartPage;
import pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void completeOrderTest() throws InterruptedException {

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        // Login
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        // Add to cart
        HomePage home = new HomePage(driver);
        home.addProductToCart();
        Thread.sleep(2000);

        home.goToCart();
        Thread.sleep(2000);

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();
        Thread.sleep(2000);

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillDetails("Amit", "Test", "12345");
        Thread.sleep(2000);

        checkout.continueCheckout();
        Thread.sleep(2000);

        checkout.finishOrder();
        Thread.sleep(2000);

        // Validation
        Assert.assertTrue(driver.getPageSource().contains("Thank you"));
    }
}