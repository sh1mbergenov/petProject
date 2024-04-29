package tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utilities.Config;
import utilities.Driver;
import utilities.SeleniumUtils;

public class TestClass {
    WebDriver driver;
    SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
    SauceDemoProductPage productPage = new SauceDemoProductPage();
    SauceDemoProductCart cart = new SauceDemoProductCart();
    SauceDemoCheckoutPage checkoutPage = new SauceDemoCheckoutPage();
    SauceDemoOverviewPage overviewPage = new SauceDemoOverviewPage();
    SauceDemoCompletePage completePage = new SauceDemoCompletePage();
    @Before
    public void navigate(){
       driver = Driver.getDriver();
       driver.get(Config.getProperty("sauceDemo"));
    }
    @Test
    public void endToEnd(){
        loginPage.username.sendKeys(Config.getProperty("sauceUser"));
        loginPage.password.sendKeys(Config.getProperty("saucePassword"));
        loginPage.loginButton.click();
        SeleniumUtils.waitForSeconds(5);
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceDemoMain"));
        productPage.backpackAddToCart.click();
        Assert.assertEquals(cart.cartButton.getText(),"1");
        cart.cartButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceDemoCart"));
        cart.checkoutButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceCheckOut"));
        checkoutPage.firstname.sendKeys(Config.getProperty("sauceFirstName"));
        checkoutPage.lastname.sendKeys(Config.getProperty("sauceLastName"));
        checkoutPage.postalCode.sendKeys(Config.getProperty("sauceZipCode"));
        checkoutPage.continueButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceOverview"));
        overviewPage.finishButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceComplete"));
        SeleniumUtils.waitForSeconds(5);
        Assert.assertEquals(completePage.confirmation.getText(),"Thank you for your order!");
        completePage.backHomeButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceDemoMain"));



    }
}
