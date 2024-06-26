package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoLoginPage {
    public SauceDemoLoginPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "user-name")
    public WebElement username;
    @FindBy(css = "input#password")
    public WebElement password;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(xpath = "//h3[text()='Epic sadface: Username and password do not match any user in this service']")
    public WebElement errorMessage;


}
