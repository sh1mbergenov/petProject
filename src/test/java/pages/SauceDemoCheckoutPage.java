package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SauceDemoCheckoutPage {
    public SauceDemoCheckoutPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "first-name")
    public WebElement firstname;
    @FindBy(id = "last-name")
    public WebElement lastname;
    @FindBy(id = "postal-code")
    public WebElement postalCode;
    @FindBy(css = "button#cancel")
    public WebElement cancelButton;
    @FindBy(id = "continue")
    public WebElement continueButton;






}
