package step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SauceDemoLoginPage;
import utilities.Config;
import utilities.Driver;


public class LoginDemoSteps {
    WebDriver driver = Driver.getDriver();
    SauceDemoLoginPage loginPage = new SauceDemoLoginPage();

    @Given("user goes to {string}")
    public void user_goes_to(String url) {
      driver.get(url);
    }
    @When("user provides a valid username")
    public void user_provides_a_valid_username() {
        loginPage.username.sendKeys(Config.getProperty("sauceUser"));
    }
    @When("user provides a valid password")
    public void user_provides_a_valid_password() {
       loginPage.password.sendKeys(Config.getProperty("saucePassword"));
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }
    @Then("verify user successfully logged in")
    public void verify_user_successfully_logged_in() {
        Assert.assertEquals(driver.getCurrentUrl(),Config.getProperty("sauceDemoMain"));
        Driver.getDriver().close();
        Driver.getDriver().quit();
    }
    @When("user provides an invalid username")
    public void user_provides_an_invalid_username() {
        loginPage.username.sendKeys(Config.getProperty("sauceLastName"));
    }
    @When("user provides an invalid password")
    public void user_provides_an_invalid_password() {
       loginPage.password.sendKeys(Config.getProperty("sauceFirstName"));
    }
    @Then("verify user sees an error message")
    public void verify_user_sees_an_error_message() {
       Assert.assertTrue(loginPage.errorMessage.isDisplayed());
    }
}
